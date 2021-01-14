package com.mininglamp.nlp.textpreprocess.charsetconverter;

import com.mininglamp.nlp.textpreprocess.charsetdetector.CharsetDetector;
import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Test extends TestCase {

    public void testConvert() throws Exception{
        String f1 = "/Users/littlebei/Program/mininglamp/java/textpreprocess/data/test/charsetconverter.big5";
        System.out.println(CharsetDetector.getCharsetType(new File(f1)));
        System.out.println(CharsetConverter.converter(new File(f1), CharsetConverter.UTF_8));

        String f2 = "/Users/littlebei/Program/mininglamp/java/textpreprocess/data/test/charsetconverter.gbk";
        System.out.println(CharsetDetector.getCharsetType(new File(f2)));
        System.out.println(CharsetConverter.converter(new File(f2), CharsetConverter.UTF_8));

        String f3 = "/Users/littlebei/Program/mininglamp/java/textpreprocess/data/test/charsetconverter.gb2312";
        System.out.println(CharsetDetector.getCharsetType(new File(f3)));
        System.out.println(CharsetConverter.converter(new File(f3), CharsetConverter.UTF_8));

        String f4 = "/Users/littlebei/Program/mininglamp/java/textpreprocess/data/test/charsetconverter.gb18030";
        System.out.println(CharsetDetector.getCharsetType(new File(f4)));
        System.out.println(CharsetConverter.converter(new File(f4), CharsetConverter.UTF_8));

        String f5 = "/Users/littlebei/Program/mininglamp/java/textpreprocess/data/test/charsetconverter.unicode";
        System.out.println(CharsetDetector.getCharsetType(new File(f5)));
        System.out.println(CharsetConverter.converter(new File(f5), CharsetConverter.UTF_8));

        String f6 = "/Users/littlebei/Program/mininglamp/java/textpreprocess/data/test/charsetconverter.utf-16";
        System.out.println(CharsetDetector.getCharsetType(new File(f6)));
        System.out.println(CharsetConverter.converter(new File(f6), CharsetConverter.UTF_8));

        /*String f6 = "/Users/littlebei/Downloads/textprocess/news.gb18030";
        long start =System.currentTimeMillis();
        //System.out.println(CharsetDetector.getCharsetType(new File(f6)));
        //CharsetConverter.converter(new File(f6), CharsetConverter.UTF_8);
        System.out.println(CharsetConverter.converter(new File(f6),CharsetConverter.UTF_8));
        long end = System.currentTimeMillis();
        System.out.println(end-start);*/
    }
}
