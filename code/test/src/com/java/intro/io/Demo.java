package com.java.intro.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Demo {
    private static final String PATH = "C:\\Users\\Administrator\\Desktop\\JAVA\\code\\test\\src\\com\\java\\intro\\io\\testfile";
    private static final String txtPath = PATH + "\\test.txt";
    private static final String jpgPath = PATH + "\\view_trees.jpg";
    private static final String imgDirectoryPath = "D:\\img";
    //ByteStream
    //  InputStream:FileInputStream;BufferedInputSteam
    //  OutputStream:FileOutputSteam;BufferedOutputSteam
    //CharStream
    //  Reader:FileReader;BufferedReader
    //  Writer:FileWriter;BufferedWriter
    public static void main(String[] args) {
//        test1();
//        File file = new File(PATH);
//        showChild(file.getParentFile().getParentFile(), "| ");
//        test3();
//        test4();
//        uploadFile(jpgPath,imgDirectoryPath);
//        test5();
        test6();
   }

    private static void test6() {
        //!! FileWriter !!
        //it will create the file automatically
        //when the programme and steam can't be close(), Flush() IS NECESSARY!!!!!!!!!
//        File file = new File(txtPath);
//        FileWriter fileWriter = new FileWriter(file);
        try (FileWriter fileWriter = new FileWriter(txtPath,true)){
            fileWriter.write("hahahahahaahahahaha");
            fileWriter.write("\n");
            fileWriter.write(106);
            char[] chars = {'A','p','p'};
            fileWriter.write(chars);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void test5() {
        //CharStream:FileReader and FileWriter
        //when the file is made by string, this is better
        File file = new File(txtPath);
        try(FileReader fileReader = new FileReader(file);){
            char[] chars = new char[20];
            int read = 0;
            //Way 3
            while ((read = fileReader.read(chars,0,chars.length))!=-1){
                System.out.println(new String(chars,0,read));
            }
            //Way 2
//            while((read = fileReader.read(chars)) != -1){
//                System.out.println(new String(chars));
//            }
            //Way 1
//            int read = fileReader.read();
//            System.out.println(read);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String uploadFile(String sourcePath, String destPath) {
        //upload an image file
        //copy file to another path
        //Buffed ways are more efficient

        //check the path
        if (sourcePath == null || sourcePath.isEmpty() || destPath == null || destPath.isEmpty()) {
            return null;
        }
        File source = new File(sourcePath);
        File dest = new File(destPath);
        //check the source isFile and exist
        if (!source.exists() || !source.isFile()){
            System.out.println("please input correct source file path");
            return null;
        }
        //if the directory is not exist, make a new one
        if(!dest.exists()){
            dest.mkdir();
        }
        //check the dest isDirectory the add the name of what to write
        if(dest.isDirectory()){
            //----------------------------
            //WARING: rename the file, otherwise the file which has the same name will be covered!!
            //----------------------------
            String filetype = source.getName().substring(source.getName().lastIndexOf("."));
            dest = new File(destPath +"\\"+ source.getName());
        }else {
            System.out.println("please input correct destination directory path");
            return null;
        }
        //copy the source file to the dest directory by byte
        try (
                //Method 1 FileInputStream
//                FileInputStream inputStream = new FileInputStream(source);
//                FileOutputStream outputStream = new FileOutputStream(dest);
                //Method 2 BufferedInputStream
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(source));
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dest));
        ) {
            //# use transfer to
            //fileInputStream.transferTo(fileOutputStream);

            long start = System.currentTimeMillis();
            //1. read the file
            int read = 0;
            while ((read = inputStream.read()) >= 0) {
                //2. write file by byte
                outputStream.write(read);
            }
            System.out.println(System.currentTimeMillis() - start);
            System.out.println("Done.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    private static void test4() {
        //FileOutputStream.write()
        //flush(),write data in buffer to file; no need to use; auto used when close() and write()
        //MUST close()!!!
        //  FileOutputStream(File file)
        //  FileOutputStream(String name, boolean append)// append: 默认值false 默认覆盖原文件数据  在末尾追加数据  true
        //  FileOutputStream(File file, boolean append)
        //  BufferedOutputStream(OutputStream out)
        String logPath = PATH + "\\demo.log";
        File file = new File(logPath);
        try (FileOutputStream stream = new FileOutputStream(file, true);) {//append default is false
            String log = "admin catch an error at 2022-1-1\n";
            //write()
            // create file if not exist
            stream.write(log.getBytes(StandardCharsets.UTF_8));//turn String to byte[]
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void test3() {
        //FileInputStream.read(...)
        //available(), get available bytes in the stream
        //MUST close()!!!
        File file = new File(txtPath);

        FileInputStream stream = null;
        try {
            stream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //check file
        if (file.isFile() && file.exists()) {
            //get FileInputStream
            try {
                byte[] bytes = new byte[1024];
                int read = 0;
                try {
                    //1. read(), return byte ascii
//                    while ((read = stream.read()) > -1) {//if no byte will return -1
//                        char c = (char) read;
//                        System.out.print(c);
//                    }
                    //2. read(byte[]), return count
                    read = stream.read(bytes);//read AND save to byte[]
                    System.out.println(new String(bytes, 0, read));//turn to string
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(read);
            } finally {//MUST CLOSE THE STREAM!!!
                //Method 1, use finally
                try {
                    if (stream != null) {//check stream is valuable
                        stream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Method 2, try(AutoCloseable){}
                //class in () need to implements Closeable interface
//                try(FileInputStream stream = new FileInputStream(file)){}
            }
        }
    }

    private static void showChild(File file, String s) {
        //递归(recursion)查询子目录
        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println(s + f.getName());
            if (f.isDirectory()) {
                showChild(f, s + s);
            }
        }
    }

    private static void test1() {
        //根据路径创建File对象
        File file = new File(PATH);
        //print the separator from Environment Variable
        //different OS hava different separator
        System.out.println(File.separator);

        //information of the File object
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        //the sum of bytes
        System.out.println(file.length());
        //last operate Msec --> DateTime
        System.out.println(new Date(file.lastModified()));
        //get parent file path String
        System.out.println(file.getParent());
        //get parent file object --> String
        System.out.println(file.getParentFile().toString());

        //checkout file
        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(file.isHidden());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
        System.out.println(file.canExecute());

        //change file/directory
        //file.delete,file.setReadOnly,file.createNewFile...
        //when file is directory and have no child, delete() works
        if (file.isDirectory() && !file.exists()) {
            file.mkdir();//add directory
        }
    }
}
