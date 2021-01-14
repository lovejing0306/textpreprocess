package com.mininglamp.nlp.textpreprocess.charsetdetector;

import com.ibm.icu.text.CharsetMatch;
import info.monitorenter.cpdetector.io.*;
import org.mozilla.universalchardet.CharsetListener;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * 字符集检测
 **/
public class CharsetDetector {
    private static CodepageDetectorProxy DETECTOR;
    private static int DETECT_SIZE = 128;

    static {
        DETECTOR = CodepageDetectorProxy.getInstance();
        DETECTOR.add(new ParsingDetector(false));
        DETECTOR.add(JChardetFacade.getInstance());
        DETECTOR.add(ASCIIDetector.getInstance());
        DETECTOR.add(UnicodeDetector.getInstance());
    }

    /**
     * 使用ICU的方式获取数据流中字符集的类型
     *
     * @param inputStream  输入检测的数据流
     * @return 返回数据流中字符集的类型
     **/
    public static String getCharsetTypeByICU(BufferedInputStream inputStream) throws Exception {
        com.ibm.icu.text.CharsetDetector detector = new com.ibm.icu.text.CharsetDetector();
        detector.setText(inputStream);
        CharsetMatch match = detector.detect();
        String encoding = match.getName();
        return encoding;
    }

    /**
     * 使用ICU的方式获取文件中字符集的类型
     *
     * @param file  输入检测的文件
     * @return 返回文件字符编码的类型
     **/
    public static String getCharsetTypeByICU(File file) throws Exception {
        String result = null;
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            result = getCharsetTypeByICU(bufferedInputStream);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            try {
                if (!Objects.isNull(fileInputStream)) {
                    fileInputStream.close();
                }
                if (!Objects.isNull(bufferedInputStream)) {
                    bufferedInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 使用CD的方式获取文件中字符集的类型
     *
     * @param file  输入检测的文件
     * @return 返回文件字符编码的类型
     **/
    public static String getCharsetTypeByCD(File file) throws Exception {
        String charsetName = null;
        try {
            Charset charset = DETECTOR.detectCodepage(file.toURI().toURL());
            charsetName = charset.name();
        } catch (IOException e) {
            throw e;
        }
        return charsetName;
    }

    /**
     * 使用CD的方式获取数据流中字符集的类型
     *
     * @param inputStream  输入检测的数据流
     * @return 返回数据流中字符集的类型
     **/
    public static String getCharsetTypeByCD(BufferedInputStream inputStream) throws Exception {
        String charsetName = null;
        try {
            Charset charset = DETECTOR.detectCodepage(inputStream, DETECT_SIZE);
            charsetName = charset.name();
        } catch (IOException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return charsetName;
    }

    /**
     * 使用UD的方式获取文件中字符集的类型
     *
     * @param file  输入检测的文件
     * @return 返回文件字符编码的类型
     **/
    public static String getCharsetTypeByUD(File file) throws Exception {
        String charsetName = null;
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            charsetName = getCharsetTypeByUD(bufferedInputStream);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            try {
                if (!Objects.isNull(fileInputStream)) {
                    fileInputStream.close();
                }
                if (!Objects.isNull(bufferedInputStream)) {
                    bufferedInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return charsetName;
    }

    /**
     * 使用UD的方式获取数据流中字符集的类型
     *
     * @param inputStream  输入检测的数据流
     * @return 返回数据流中字符集的类型
     **/
    public static String getCharsetTypeByUD(BufferedInputStream inputStream) throws Exception {
        String charsetName = null;
        try {
            byte[] b = new byte[DETECT_SIZE];
            inputStream.read(b);
            UniversalDetector detector = new UniversalDetector(new CharsetListener() {
                @Override
                public void report(String charset) {
                    //System.out.println(charset);
                    return;
                }
            });
            detector.handleData(b, 0, b.length);
            detector.dataEnd();
            charsetName = detector.getDetectedCharset();
        } catch (IOException e) {
            throw e;
        }
        return charsetName;
    }

    /**
     * 获取文件中字符集的类型
     *
     * @param file  输入检测的文件
     * @return 返回文件字符编码的类型
     **/
    public static String getCharsetType(File file) throws Exception {
        return getCharsetTypeByUD(file);
    }

    /**
     * 获取数据流中字符集的类型
     *
     * @param inputStream  输入检测的数据流
     * @return 返回数据流中字符集的类型
     **/
    public static String getCharsetType(BufferedInputStream inputStream) throws Exception {
        return getCharsetTypeByUD(inputStream);
    }
}
