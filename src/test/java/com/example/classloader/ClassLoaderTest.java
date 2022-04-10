package com.example.classloader;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: luozijian
 * @date: 7/24/21 15:00:02
 * @description:
 */
public class ClassLoaderTest {

    /**
     * 查看父类加载器
     */
    @Test
    public void test1() {
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类装载器:" + appClassLoader);
        ClassLoader extensionClassLoader = appClassLoader.getParent();
        System.out.println("系统类装载器的父类加载器——扩展类加载器:" + extensionClassLoader);
        ClassLoader bootstrapClassLoader = extensionClassLoader.getParent();
        System.out.println("扩展类加载器的父类加载器——引导类加载器:" + bootstrapClassLoader);


    }

    /**
     * 对象只加载一次，返回true
     */
    @Test
    public void test4() {
        ClassLoader c1 = ClassLoaderTest.class.getClassLoader();
        ClassLoaderTest loadtest = new ClassLoaderTest();
        ClassLoader c2 = loadtest.getClass().getClassLoader();
        System.out.println("c1.equals(c2):"+c1.equals(c2));
    }


    /**
     * 父类classloader
     * @throws Exception
     */
    @Test
    public  void test2() throws Exception{
        MyClassLoader loader = new MyClassLoader();

//        Class<?> c = loader.loadClass("com.example.classloader.HighRichHandsome");
        Class<?> c = loader.findClass("com.example.classloader.HighRichHandsome");

        System.out.println("Loaded by :" + c.getClassLoader());


        /**
         * 不会报错，因为Person是AppClassLoader加载，MyClassLoader可以看得见
         */
        Person p = (Person) c.newInstance();
        p.say();

        /**
         * 报错，c产生的对象对以AppClassLoader加载器加载的HighRichHandsome类不可见
         */
        HighRichHandsome man = (HighRichHandsome) c.newInstance();
        man.say();
    }


    public static class MyClassLoader extends ClassLoader{
        /*
         * 覆盖了父类的findClass，实现自定义的classloader
         */
        @Override
        public Class<?> findClass(String name) {
            byte[] bt = loadClassData(name);
            return defineClass(name, bt, 0, bt.length);
        }

        private byte[] loadClassData(String className) {
            InputStream is = getClass().getClassLoader().getResourceAsStream(
                    className.replace(".", "/") + ".class");
            ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
            int len = 0;
            try {
                while ((len = is.read()) != -1) {
                    byteSt.write(len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return byteSt.toByteArray();
        }
    }



    @Test
    public void test(){

        ClassLoader c1 = this.getClass().getClassLoader();
        System.out.println(c1);

        ClassLoader c2 = c1.getParent();
        System.out.println(c2);

        ClassLoader c3 = c2.getParent();
        System.out.println(c3);


    }

}