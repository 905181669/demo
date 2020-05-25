package com.example.tomcat;

import java.io.OutputStream;

/**
 * @author: luozijian
 * @date: 2020-04-21 21:48:05
 * @description:
 */
public class GPResponse {

    private OutputStream out;

    public GPResponse(OutputStream out) {
        this.out = out;
    }

    public void write(String s) throws Exception{

        StringBuilder sb = new StringBuilder();

        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(s);
        out.write(sb.toString().getBytes());
    }
}
