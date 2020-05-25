package com.example.tomcat;

/**
 * @author: luozijian
 * @date: 2020-04-21 22:06:16
 * @description:
 */
public class SecondServlet extends GPServlet {

    @Override
    public void service(GPRequest request, GPResponse response) throws Exception {
        super.service(request, response);
    }

    @Override
    public void doGet(GPRequest request, GPResponse response) throws Exception {
        this.doPost(request ,response);
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("this is second servlet");
    }
}
