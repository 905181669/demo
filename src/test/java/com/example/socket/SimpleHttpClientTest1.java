package com.example.socket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: luozijian
 * @date: 9/6/21 08:25:31
 * @description:
 */
@Slf4j
public class SimpleHttpClientTest1 {

    boolean isConnected = false;
    String host;
    Socket socket;

    int OTHER = 200;
    int TRANSFER_ENCODING = 302;
    int CONTENT_LENGHT = 0;

    public static void main(String[] args) {
        String raw = "http://localhost:9090";
        SimpleHttpClientTest1 client = new SimpleHttpClientTest1();

        try {
            URL url = new URL(raw);
            client.connect(url.getHost(), url.getPort());

            StringBuffer buf = new StringBuffer();
            buf.append("GET /echo?content=1 HTTP/1.1");
            buf.append("\r\n");
            buf.append("Content-Type: text/plain");
            buf.append("\r\n");
            buf.append("User-Agent: restclient/1.0");
            buf.append("\r\n");
            buf.append("Connection: keep-alive"); //保持连接，单路复用
            buf.append("\r\n");
            buf.append("Accept-Encoding: *;q=0"); //不支持任何压缩编码
            buf.append("\r\n");
            buf.append("Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
            buf.append("\r\n");
            buf.append("Host: " + url.getHost() + ":" + url.getPort());
            buf.append("\r\n");
            buf.append("Content-Length: 0");
            buf.append("\r\n");
            buf.append("\r\n");

            for(int i = 0; i < 1; i++) {
                Response resp = client.send(buf.toString());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            client.disconnect();
        }
    }


    public boolean connect(String ip, int port){
        try{
            this.disconnect();

            host = ip + ":" + port;
            socket = new Socket();
            socket.setSoTimeout(5000);
            SocketAddress addr = new InetSocketAddress(ip, port);
            socket.connect(addr);
            isConnected = true;
        }catch(Exception e) {
            log.error("打开" + ip + ":" + port + "接口失败：", e);
        }

        return isConnected;
    }

    /**
     * 发送请求并读取响应数据
     */
    public Response send(String req) throws IOException {
        Response result = null;

        if(this.isConnected) {
            log.debug(host + "请求：\n" + req);
            OutputStream out = socket.getOutputStream();
            //发送请求
            out.write(req.getBytes());
            out.flush();

            InputStream in = socket.getInputStream();
            //读取响应
            byte[] bytes = this.readResp(in);
            if(bytes.length > 0) {
                String resp = new String(bytes).trim();
                log.debug(host + "响应：\n" + resp);
                result = new Response(resp);
            }
        }

        return result;
    }

    /**
     * 读取响应数据
     */
    private byte[] readResp(InputStream in) throws IOException {
        byte[] bytes = new byte[1024 << 2];
        int i = 0;
        int endFlagCounter = 0;

        //读取响应头
        do {
            int val = in.read();
            byte b = (byte)val;
            if(b == '\r') {
                continue;
            }

            bytes[i++] = b;

            if(i >= bytes.length) {
                break;
            }

            if(b == '\n') {
                endFlagCounter++;
            }else{
                endFlagCounter = 0;
            }

            if(endFlagCounter == 2) {
                break;
            }
        }while(true);

        bytes = ArrayUtils.subarray(bytes, 0, i);
        //读取响应体
        String respBody = this.readRespBody(bytes, in);
        if(StringUtils.isNotBlank(respBody)) {
            bytes = ArrayUtils.addAll(bytes, respBody.getBytes());
        }

        return bytes;
    }

    /**
     * 提取content-length的数值
     */
    private int getContentLength(String[] headers){
        int len = -1;

        for(String s : headers) {
            String headerLine = s.trim().toLowerCase();
            if(headerLine.startsWith("content-length")) {
                String[] kv = s.split(":");
                len = Integer.parseInt(kv[1].trim());
            }
        }

        return len;
    }

    /**
     * 识别服务返回responseBody的方式
     */
    private int selectReadingMode(String[] headers) {
        int result = OTHER;

        for(String s : headers) {
            String headerLine = s.trim().toLowerCase();
            if(StringUtils.equals(headerLine, "transfer-encoding: chunked")) {
                result = TRANSFER_ENCODING;
                break;
            }else if(headerLine.startsWith("content-length")){
                result = CONTENT_LENGHT;
                break;
            }
        }

        return result;
    }

    /**
     * 读取响应体数据
     */
    private String readRespBody(byte[] bytes, InputStream in) throws IOException {
        String result = null;
        byte[] respBody = null;
        String header = new String(bytes);
        String[] headers = header.split("\n");
        int readingMode = this.selectReadingMode(headers);
        int blankLineNum = 0;

        if (readingMode == TRANSFER_ENCODING) {
            do {
                byte[] lineBytes = this.readLine(in);
                String line = new String(lineBytes).trim();
                if(StringUtils.isBlank(line)) {
                    blankLineNum++;
                    if(blankLineNum >= 2) { //连续两个换行符结束读取
                        break;
                    }else {
                        continue;
                    }
                }

                blankLineNum = 0;
                if(false == line.matches("^[\\dabcdefABCDEF]+$")){
                    continue;
                }

                int size = Integer.parseInt(line, 16);
                if(size > 0) {
                    byte[] block = this.read(in, size);

                    if(respBody == null) {
                        respBody = block;
                    }else{
                        respBody = ArrayUtils.addAll(respBody, block);
                    }
                }else{
                    break;
                }
            }while(true);
        }else if(readingMode == CONTENT_LENGHT){
            int len = getContentLength(headers);
            if(len > 0) {
                respBody = this.read(in, len);
            }
        }

        int remain = in.available();
        if(remain > 0) {
            //提取余下的数据不作处理，为了不响应下次读取
            this.read(in, remain);
        }

        if(respBody != null) {
            result = new String(respBody);
        }

        return result;
    }

    /**
     * 读取指定大小的数据块
     */
    private byte[] read(InputStream in, int size) throws IOException {
        byte[] bytes = new  byte[size];
        int offset = 0;
        int len = size;
        int num = -1;
        do{
            num = in.read(bytes, offset, len);
            if(num == -1) {
                break;
            }
            offset += num;
            len = size - offset;
        }while(offset < size);

        if(offset < size) {
            return Arrays.copyOfRange(bytes, 0, offset);
        }else{
            return bytes;
        }
    }

    /**
     * 读取一行数据
     */
    private byte[] readLine(InputStream in) throws IOException {
        ByteArrayOutputStream byteBuf = new ByteArrayOutputStream();

        do{
            byte c = (byte)in.read();
            if(c == '\r') {
                continue;
            }

            byteBuf.write(c);
            if(c == '\n') {
                break;
            }
        }while(true);

        return byteBuf.toByteArray();
    }

    public void disconnect(){
        try {
            if(this.socket != null && false == this.socket.isClosed()) {
                this.socket.close();
            }
        } catch (IOException e) {
            log.error(host + "关闭连接失败：", e);
        }

        this.isConnected = false;
    }


    public static class Response {

        private Map<String, String> headerMap;

        private int statusCode;

        private String body;

        private String respString;

        public Response(String resp){
            this.headerMap = new HashMap<>();
            this.respString = resp;

            if(StringUtils.isNotBlank(resp.trim())) {
                String[] lines = resp.trim().split("\n");

                if (lines[0].length() > 9) {
                    String line = lines[0].substring(9);
                    this.statusCode = Integer.parseInt(line.substring(0, 3));
                }

                int i = 1;
                for(; i < lines.length; i++) {
                    if(StringUtils.isBlank(lines[i])) {
                        break;
                    }

                    String[] keyValue = lines[i].split(":", 2);
                    headerMap.put(keyValue[0], keyValue[1].trim());
                }

                StringBuffer buf = new StringBuffer();
                for(i+=1; i  < lines.length; i++) {
                    buf.append(lines[i]);
                    buf.append("\n");
                }
                this.body = buf.toString();
            }
        }

        public Map<String, String> getHeaderMap() {
            return headerMap;
        }

        public void setHeaderMap(Map<String, String> headerMap) {
            this.headerMap = headerMap;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        @Override
        public String toString(){
            return this.respString;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }
    }
}