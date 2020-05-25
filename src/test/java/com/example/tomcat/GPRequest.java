package com.example.tomcat;

import java.io.InputStream;

/**
 * @author: luozijian
 * @date: 2020-04-21 21:43:51
 * @description:
 */

public class GPRequest {

    private String method;

    private String url;

    public GPRequest(InputStream in) {

        try{
            String content = "";
            byte[] buff = new byte[2048];
            int len = 0;
            if((len = in.read(buff)) > 0){
                content = new String(buff, 0, len);
            }

            String line = content.split("\\?")[0];
            String[] arr = line.split("\\s");

            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
