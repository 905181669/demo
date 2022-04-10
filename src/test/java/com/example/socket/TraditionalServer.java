package com.example.socket;

import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class TraditionalServer {


	static ExecutorService executorService = Executors.newSingleThreadExecutor();

    
    public static void main(String args[]) {
	
	int port = 2000;
	ServerSocket server_socket;
	DataInputStream input;
	
	try {
	    
	    server_socket = new ServerSocket(port);
	    System.out.println("Server waiting for client on port " + 
			       server_socket.getLocalPort());
	    
	    // server infinite loop
	    while(true) {
			Socket socket = server_socket.accept();

			System.out.println("New connection accepted " +
					   socket.getInetAddress() +
					   ":" + socket.getPort());

			try {


				//异步执行耗时的操作
				Future f = executorService.submit(()->{

					try{
						byte[] byteArray = new byte[4096];

						String fname = "/Users/luozijian/Downloads/to_generatorConfig.xml";

						File file = new File(fname);
						InputStream is = new FileInputStream(file);
						is.read(byteArray);

						OutputStream out = socket.getOutputStream();

						Thread.sleep(5000);
						out.write(byteArray);
					}catch (Exception e){
						e.printStackTrace();
					}

				});
				f.get();

			}
			catch (Exception e) {
				System.out.println(e);
			}

			// connection closed by client
			try {
				socket.close();
				System.out.println("Connection closed by client");
			}
		catch (IOException e) {
		    	System.out.println(e);
			}
		
	    }
	    
	    
	}
	
	catch (IOException e) {
	    System.out.println(e);
	}
    }
}
