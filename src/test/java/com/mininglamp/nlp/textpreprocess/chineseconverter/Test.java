package com.mininglamp.nlp.textpreprocess.chineseconverter;


import junit.framework.TestCase;

import java.io.File;
import java.util.Date;


public class Test extends TestCase {

    public void testChinese() throws Exception{
        String s = "生命不息奮鬥不止";
        String s1 = "生命不息，奋斗不止";
        System.out.println(ChineseConverter.simpleToTraditional(s));
        System.out.println(ChineseConverter.traditionalToSimple(s));



        String f1="/Users/littlebei/Program/mininglamp/java/textpreprocess/data/test/charsetconverter.big5";
        //ChineseConverter.traditionalToSimple(new File(f1));
        System.out.println(ChineseConverter.traditionalToSimple(new File(f1)));

        /*String f2="/Users/littlebei/Downloads/textprocess/news_1w.txt";
        long start =System.currentTimeMillis();
        ChineseConverter.simpleToTraditional(new File(f2));
        //System.out.println(ChineseConverter.simpleToTraditional(new File(f2)));
        long end = System.currentTimeMillis();
        System.out.println(end-start);*/

        System.out.println(ChineseConverter.detectChineseType(s1));
    }
}
