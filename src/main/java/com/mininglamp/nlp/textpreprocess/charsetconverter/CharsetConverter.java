package com.mininglamp.nlp.textpreprocess.charsetconverter;

import com.mininglamp.nlp.textpreprocess.charsetdetector.CharsetDetector;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 对字符集进行转换
 **/
public class CharsetConverter {
    public static final String GBK = "GBK";
    public static final String GB2312 = "GB2312";
    public static final String UTF_8 = "UTF-8";
    public static final String UNICODE = "UNICODE";
    public static final String GB18030 = "GB18030";
    public static final String BIG5 = "Big5";

    /**
     * 对文件中的字符集进行转换
     *
     * @param file  输入要转换的文件
     * @param charset   指定转换后的字符集
     * @return 返回转换后的字符串
     **/
    public static String converter(File file, String charset) throws Exception {
        String result = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            result = converter(fileInputStream, CharsetDetector.getCharsetType(file), charset);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            try {
                if (!Objects.isNull(fileInputStream)) {
                    fileInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 对数据流中的字符集进行转换
     *
     * @param inputStream  输入要转换的字符流
     * @param charset   指定转换后的字符集
     * @return 返回转换后的字符串
     **/
    public static String converter(BufferedInputStream inputStream, String charset) throws Exception {
        String result = null;
        try {
            result = converter(inputStream, CharsetDetector.getCharsetType(inputStream), charset);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * 对文件中的字符集进行转换
     *
     * @param file  输入要转换的文件
     * @param oldCharset   指定转换前的字符集
     * @param newCharset   指定转换后的字符集
     * @return 返回转换后的字符串
     **/
    public static String converter(File file, String oldCharset, String newCharset) throws Exception {
        String result = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            result = converter(fileInputStream, oldCharset, newCharset);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            try {
                if (!Objects.isNull(fileInputStream)) {
                    fileInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 对数据流中的字符集进行转换
     *
     * @param inputStream  输入要转换的字符流
     * @param oldCharset   指定转换前的字符集
     * @param newCharset   指定转换后的字符集
     * @return 返回转换后的字符串
     **/
    public static String converter(InputStream inputStream, String oldCharset, String newCharset) throws Exception {
        String result = null;
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            List<String> list = new ArrayList<>();
            read = new InputStreamReader(inputStream, oldCharset);// 考虑到编码格式
            bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                lineTxt = CharsetConverter.convertCharset(lineTxt, newCharset, newCharset);
                list.add(lineTxt);
            }
            result = String.join("\n", list);
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (!Objects.isNull(read)) {
                    read.close();
                }
                if (!Objects.isNull(bufferedReader)) {
                    bufferedReader.close();
                }
            } catch (Exception e) {

            }
        }
        return result;
    }

    private static String convertCharset(String str, String newCharset) throws Exception {
        String result = null;
        try {
            byte[] bs = str.getBytes();// 用默认字符编码解码字符串
            result = new String(bs, newCharset);// 用新的字符编码生成字符串
        } catch (UnsupportedEncodingException e) {
            throw e;
        }
        return result;
    }

    private static String convertCharset(String original, String oldCharset, String newCharset) throws Exception {
        String result = null;
        try {
            // 用旧的字符编码解码字符串。解码可能会出现异常。
            byte[] bs = original.getBytes(oldCharset);
            // 用新的字符编码生成字符串
            result = new String(bs, newCharset);
        } catch (UnsupportedEncodingException e) {
            throw e;
        }
        return result;
    }
}
