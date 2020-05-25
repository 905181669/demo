package com.example.tomcat;

/**
 * @author: luozijian
 * @date: 2020-04-21 21:42:46
 * @description:
 */
public abstract class GPServlet {

    public void service(GPRequest request, GPResponse response) throws Exception{
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request, response);
        }else {
            doPost(request, response);
        }
    }


    public abstract void doGet(GPRequest request, GPResponse response)throws Exception;

    public abstract void doPost(GPRequest request, GPResponse response)throws Exception;

}
