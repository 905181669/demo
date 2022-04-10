package com.example.socket;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 堆内存-》直接内存-》系统调用-》硬盘/网卡
 */
public class TraditionalClient {
    
    
    
    public static void main(String[] args) {

	int port = 2000;
	String server = "localhost";
	Socket socket = null;
	String lineToBeSent;
	
	DataOutputStream output = null;
	FileInputStream inputStream = null;
	int ERROR = 1;
	
	
	// connect to server
	try {
	    socket = new Socket(server, port);
	    System.out.println("Connected with server " +
				   socket.getInetAddress() +
				   ":" + socket.getPort());
	}
	catch (UnknownHostException e) {
	    System.out.println(e);
	    System.exit(ERROR);
	}
	catch (IOException e) {
	    System.out.println(e);
	    System.exit(ERROR);
	}
	
	try {
		String fname = "/Users/luozijian/Downloads/software/apache-jmeter-5.3.tgz";

		//2298个字节
//		String fname = "/Users/luozijian/Downloads/generatorConfig.xml";
		inputStream = new FileInputStream(fname);
		
	    output = new DataOutputStream(socket.getOutputStream());
	    long start = System.currentTimeMillis();
	    //如果文件很大，这样就会占用很多内存
//	    byte[] b = new byte[inputStream.available()];

		//这样在写时循环使用这个字节数组
		byte[] b = new byte[4096];
	    int read = 0, total = 0;

	    //读到文件未返回-1
	    while((read = inputStream.read(b))>=0) {

	    	//只写循环读到的数量
			output.write(b, 0 ,read);
			//这样会把最后的1024整个数组write出去
//			output.write(b);


//	    	System.out.println("client长度：" + read);
			total = total + read;

	    }
	    System.out.println("bytes send--"+total+" and totaltime--"+(System.currentTimeMillis() - start));
	}
	catch (IOException e) {
	    System.out.println(e);
	}

	try {
		output.close();
	    socket.close();
	    inputStream.close();
	}
	catch (IOException e) {
	    System.out.println(e);
	}
    }    
}
