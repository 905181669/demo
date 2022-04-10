package com.example.socket;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

/**
 * @author: luozijian
 * @date: 4/19/21 20:10:10
 * @description:
 */
@Slf4j
public class NetworkTest {

    public static void main(String[] args) {
        byte[] bytes = getByteArrayFromUrl("http://www.baidu.com");
        System.out.println(new String(bytes));
    }

    public static byte[] getByteArrayFromUrl(String urlPath) {
        byte[] data = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(200000);
            is = conn.getInputStream();

            if (conn.getResponseCode() == 200) {
                data = readInputStream(is);
            } else{
                data = null;
            }
        } catch (Exception e) {
            log.error("获取图片异常", e.getMessage(), e);
        }  finally {
            IOUtils.closeQuietly(is);
            // 关闭连接
            conn.disconnect();
        }
        return data;
    }


    /**
     * 将流转换为字节
     * @param is
     * @return
     */
    public static byte[] readInputStream(InputStream is) {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } catch (Exception e) {
            log.error("读取流异常", e.getMessage(), e);
        }finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
        return os.toByteArray();
    }


    @Test
    public  void test() throws Exception{

        int PORT = 9090;
        String HOST = "127.0.0.1";

        Socket socket = new Socket();
        SocketAddress address = new InetSocketAddress(HOST, PORT);
        socket.connect(address);

        OutputStream output = socket.getOutputStream();

    }
}