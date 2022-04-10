package com.example.demo;

import lombok.Data;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;

/**
 * @author: luozijian
 * @date: 2/1/21 16:36:01
 * @description:
 */
public class SerialzableTest {



    public static void main(String[] args) {

        SerialzableTest test = new SerialzableTest();
        test.write();
        test.read();



    }

    private void write(){

        User test = new User();
        test.setName("luozijian");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("/Users/luozijian/Downloads/mygitsource/demo/src/test/java/com/example/demo/tempFile"));
            oos.writeObject(test);



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(oos);
        }
    }


    private void read(){
        File file = new File("/Users/luozijian/Downloads/mygitsource/demo/src/test/java/com/example/demo/tempFile");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            User newUser = (User) ois.readObject();
            System.out.println(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(ois);
//            try {
//                FileUtils.forceDelete(file);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

}

@Data
class User implements Serializable{

    private static final long serialVersionUID = 1L;

    private String name;

    private int age;

}