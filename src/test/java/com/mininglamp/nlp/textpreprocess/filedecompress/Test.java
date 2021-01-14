package com.mininglamp.nlp.textpreprocess.filedecompress;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileInputStream;

public class Test extends TestCase {

    public void testCompress() throws Exception{
        /*String target1="/Users/littlebei/Downloads/textprocess/decompress/";
        File file1 = new File("/Users/littlebei/Downloads/textprocess/test.zip");
        FileInputStream fileInputStream1=new FileInputStream(file1);
        long start =System.currentTimeMillis();
        FileDecompress.decompressZip(fileInputStream1, target1);
        //FileDecompress.decompressZip(file1, target1);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*/

        /*String target2="/Users/littlebei/Downloads/textprocess/decompress/";
        File file2 = new File("/Users/littlebei/Downloads/textprocess/test.tar");
        FileInputStream fileInputStream2=new FileInputStream(file2);
        long start =System.currentTimeMillis();
        FileDecompress.decompressTar(fileInputStream2, target2);
        //FileDecompress.decompressTar(file2, target2);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*/

        /*String target3="/Users/littlebei/Downloads/textprocess/decompress/";
        File file3 = new File("/Users/littlebei/Downloads/textprocess/test.tar.gz");
        FileInputStream fileInputStream3=new FileInputStream(file3);
        long start =System.currentTimeMillis();
        //FileDecompress.decompressTarGz(fileInputStream3, target3);
        FileDecompress.decompressTarGz(file3, target3);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*/

        /*String target4="/Users/littlebei/Downloads/textprocess/decompress/";
        File file4 = new File("/Users/littlebei/Downloads/textprocess/news.utf-8.gz");
        FileInputStream fileInputStream4=new FileInputStream(file4);
        long start =System.currentTimeMillis();
        FileDecompress.decompressGz(fileInputStream4, target4);
        //FileDecompress.decompressGz(file4, target4);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*/

        /*String target5="/Users/littlebei/Downloads/textpreprocess/decompress/zip/";
        File file5 = new File("/Users/littlebei/Downloads/textpreprocess/decompress/11.7z");
        long start =System.currentTimeMillis();
        FileDecompress.decompress7Z(file5, target5, "123");
        long end = System.currentTimeMillis();
        System.out.println(end-start);*/

        String target6="/Users/littlebei/Downloads/textpreprocess/decompress/zip/";
        File file6 = new File("/Users/littlebei/Downloads/textpreprocess/decompress/test1.rar");
        long start =System.currentTimeMillis();
        FileDecompress.decompressRAR(file6, target6);
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        /*String target7="/Users/littlebei/Downloads/textpreprocess/decompress/zip/";
        File file7 = new File("/Users/littlebei/Downloads/textpreprocess/decompress/evdata01.zip");
        //FileInputStream fileInputStream7=new FileInputStream(file7);
        long start =System.currentTimeMillis();
        FileDecompress.decompressZip(file7, target7, null);
        //FileDecompress.decompressGz(file4, target4);
        long end = System.currentTimeMillis();
        System.out.println(end-start);*/
    }
}
