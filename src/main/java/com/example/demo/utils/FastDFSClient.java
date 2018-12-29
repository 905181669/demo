package com.example.demo.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.model.FastDFSFile;

public class FastDFSClient {

	private final static Logger logger = LoggerFactory.getLogger(FastDFSClient.class);
	static {
	    try {
	        String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();;
	        ClientGlobal.init(filePath);
	    } catch (Exception e) {
	    	e.printStackTrace();
	        logger.error("FastDFS Client Init Fail!",e);
	    }
	}
	
	
	public static String[] upload(FastDFSFile file) {
	    logger.info("File Name: " + file.getName() + "File Length:" + file.getContent().length);

	    //文件属性信息
	    NameValuePair[] meta_list = new NameValuePair[1];
	    meta_list[0] = new NameValuePair("author", file.getAuthor());

	    long startTime = System.currentTimeMillis();
	    String[] uploadResults = null;
	    StorageClient storageClient=null;
	    try {
	        //获取 
	        storageClient = getStorageClient();
	        //上传
	        uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
	    } catch (IOException e) {
	        logger.error("IO Exception when uploadind the file:" + file.getName(), e);
	    } catch (Exception e) {
	        logger.error("Non IO Exception when uploadind the file:" + file.getName(), e);
	    }
	    logger.info("upload_file time used:" + (System.currentTimeMillis() - startTime) + " ms");

	    //验证上传结果
	    if (uploadResults == null && storageClient!=null) {
	        logger.error("upload file fail, error code:" + storageClient.getErrorCode());
	    }
	    //上传文件成功会返回 groupName。
	    logger.info("upload file successfully!!!" + "group_name:" + uploadResults[0] + ", remoteFileName:" + " " + uploadResults[1]);
	    return uploadResults;
	}
	
	private static StorageClient getStorageClient() throws IOException {
	    TrackerServer trackerServer = getTrackerServer();
	    StorageClient storageClient = new StorageClient(trackerServer, null);
	    return  storageClient;
	}
	
	private static TrackerServer getTrackerServer() throws IOException {
	    TrackerClient trackerClient = new TrackerClient();
	    TrackerServer trackerServer = trackerClient.getConnection();
	    return  trackerServer;
	}
	
	/**
	 * 获取文件
	 * @param groupName
	 * @param remoteFileName
	 * @return
	 */
	public static FileInfo getFile(String groupName, String remoteFileName) {
	    try {
	        storageClient = new StorageClient(trackerServer, storageServer);
	        return storageClient.get_file_info(groupName, remoteFileName);
	    } catch (IOException e) {
	        logger.error("IO Exception: Get File from Fast DFS failed", e);
	    } catch (Exception e) {
	        logger.error("Non IO Exception: Get File from Fast DFS failed", e);
	    }
	    return null;
	}
	
	public static InputStream downFile(String groupName, String remoteFileName) {
	    try {
	        StorageClient storageClient = getStorageClient();
	        byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
	        InputStream ins = new ByteArrayInputStream(fileByte);
	        return ins;
	    } catch (IOException e) {
	        logger.error("IO Exception: Get File from Fast DFS failed", e);
	    } catch (Exception e) {
	        logger.error("Non IO Exception: Get File from Fast DFS failed", e);
	    }
	    return null;
	}
	
	/**
	 * 删除文件
	 * @param groupName
	 * @param remoteFileName
	 * @throws Exception
	 */
	public static void deleteFile(String groupName, String remoteFileName)
	        throws Exception {
	    StorageClient storageClient = getStorageClient();
	    int i = storageClient.delete_file(groupName, remoteFileName);
	    logger.info("delete file successfully!!!" + i);
	}
	
	
}