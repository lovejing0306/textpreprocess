package com.mininglamp.nlp.textpreprocess.fileparse;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Test extends TestCase {
    public void testParser() throws Exception{
        /*String s1 = "/Users/littlebei/Downloads/1.html";
        File file1 = new File(s1);
        System.out.println(FileParse.parseHtml(file1));

        String s2="/Users/littlebei/Downloads/pom.xml";
        File file2=new File(s2);
        System.out.println(FileParse.parseXML(file2));

        String s3="/Users/littlebei/Downloads/实体链接/基于多知识库的短文本实体链接方法研究_——以Wikipedia和Freebase为例.pdf";
        File file3=new File(s3);
        System.out.println(FileParse.parsePDF(file3));

        String s4="/Users/littlebei/Downloads/潍坊出差报销2.xlsx";
        File file4=new File(s4);
        System.out.println(FileParse.parseXlsx(file4));

        String s5="/Users/littlebei/Downloads/机器总结/机器学习总结-模型评估.docx";
        File file5=new File(s5);
        System.out.println(FileParse.parseDocx(file5));


        String s6="/Users/littlebei/Downloads/1.rtf";
        File file6=new File(s6);
        System.out.println(FileParse.parseRTF(file6));


        String s7="/Users/littlebei/Downloads/xxxxx.docx";
        File file7=new File(s7);
        System.out.println(FileParse.parseDocx(file7));*/

        /*String s8="/Users/littlebei/Downloads/知识图谱发展报告.pdf";
        File file8=new File(s8);
        FileInputStream fileInputStream8 = new FileInputStream(file8);
        long start =System.currentTimeMillis();
        PDFParser.parser(fileInputStream8);
        System.out.println(PDFParser.parser(new FileInputStream(file8)));
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        String s9="/Users/littlebei/Downloads/textprocess/标点句统计分析(定稿原文).doc";
        File file9=new File(s9);
        FileInputStream fileInputStream9 =new FileInputStream(file9);
        start =System.currentTimeMillis();
        DocParser.parser(fileInputStream9);
        //System.out.println(FileParse.parseDoc(new FileInputStream(file9)));
        end = System.currentTimeMillis();
        System.out.println(end-start);

        String s10="/Users/littlebei/Downloads/textprocess/机器学习总结-模型简介.docx";
        File file10=new File(s10);
        FileInputStream fileInputStream10 = new FileInputStream(file10);
        start =System.currentTimeMillis();
        DocxParser.parser(fileInputStream10);
        //System.out.println(FileParse.parseDocx(new FileInputStream(file10)));
        end = System.currentTimeMillis();
        System.out.println(end-start);

        String s11="/Users/littlebei/Downloads/textprocess/包头社会化信息原始表.xlsx";
        File file11=new File(s11);
        FileInputStream fileInputStream11 = new FileInputStream(file11);
        start =System.currentTimeMillis();
        XLSXParser.parser(fileInputStream11);
        //System.out.println(FileParse.parseXlsx(new FileInputStream(file11)));
        end = System.currentTimeMillis();
        System.out.println(end-start);

        String s12="/Users/littlebei/Downloads/textprocess/知识图谱发展报告.pdf";
        File file12=new File(s12);
        FileInputStream fileInputStream12=new FileInputStream(file12);
        start =System.currentTimeMillis();
        PDFParser.parser(fileInputStream12);
        //System.out.println(FileParse.parsePDF(new FileInputStream(file8)));
        end = System.currentTimeMillis();
        System.out.println(end-start);

        String s13="/Users/littlebei/Downloads/textprocess/中文信息处理发展报告2016.pdf";

        File file13=new File(s13);
        FileInputStream fileInputStream13=new FileInputStream(file13);
        start =System.currentTimeMillis();
        PDFParser.parser(fileInputStream13);
        //System.out.println(FileParse.parsePDF(new FileInputStream(file8)));
        end = System.currentTimeMillis();
        System.out.println(end-start);*/

        String s14 = "/Users/littlebei/Downloads/textpreprocess/eml_files/Re_ 2018发布会工作会议-6.28.eml";
        File file14 = new File(s14);
        System.out.println(EMLParser.parser(file14));
        //InputStream inputStream=new FileInputStream(file14);
        //System.out.println(EMLParser.parser(inputStream));
    }

    /*public void testEML(){
        String dir = "/Users/mxf/eml_files";
        File file = new File(dir);
        for (File fp: file.listFiles()){
            System.out.println(fp.getName());
            System.out.println(EMLParser.parser(fp));
        }
    }*/
}
