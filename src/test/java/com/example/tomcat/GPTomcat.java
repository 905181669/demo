package com.example.tomcat;

import com.google.common.collect.Maps;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;

/**
 * @author: luozijian
 * @date: 2020-04-21 22:11:54
 * @description:
 */
public class GPTomcat {


    private int port = 8080;
    private ServerSocket server;
    private Map<String, GPServlet> servletMapping = Maps.newHashMap();

    private Properties webxml = new Properties();

    public void init(){
        try{

            String WEB_INF = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");

            webxml.load(fis);

            for(Object k : webxml.keySet()){
                String key = k.toString();
                if(key.endsWith(".url")){
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);

                    String className = webxml.getProperty(servletName + ".className");
                    GPServlet obj = (GPServlet)Class.forName(className).newInstance();
                    servletMapping.put(url, obj);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void start(){
        init();
        try{

            server = new ServerSocket(this.port);
            System.out.println("GPTomcat 已启动，监听的端口为：" + this.port);

            while (true){
                Socket client = server.accept(); //阻塞
                process(client);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void process(Socket client) throws Exception{
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();

        GPRequest request = new GPRequest(is);
        GPResponse response = new GPResponse(os);

        String url = request.getUrl();
        System.out.println(url);

        if(servletMapping.containsKey(url)){
            servletMapping.get(url).service(request, response);
        }else {
            response.write("<img src= 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587491480351&di=f8cfcf5309268968a3bd4d16ce559b53&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fd009b3de9c82d1587e249850820a19d8bd3e42a9.jpg' /> \n");
        }

        os.flush();
        os.close();

        is.close();
        client.close();

    }


    public static void main(String[] args) {
        new GPTomcat().start();
    }

}
