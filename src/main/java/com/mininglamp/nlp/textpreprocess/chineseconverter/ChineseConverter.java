package com.mininglamp.nlp.textpreprocess.chineseconverter;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.mininglamp.nlp.textpreprocess.charsetdetector.CharsetDetector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 中文简繁体转换
 **/
public class ChineseConverter {
    private static final String SIMPLE = "simple";
    private static final String TRADITIONAL = "traditional";
    private static final String GB2312 = "GB2312";

    /**
     * 对文件进行简体转繁体
     *
     * @param file  输入要转换的文件
     * @return 返回转换后的字符串
     **/
    public static String simpleToTraditional(File file) throws Exception {
        String result = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            result = simpleToTraditional(inputStream, CharsetDetector.getCharsetType(file));
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            try {
                if (!Objects.isNull(inputStream)) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 对数据流进行简体转繁体
     *
     * @param inputStream  输入要转换的数据流
     * @return 返回转换后的字符串
     **/
    public static String simpleToTraditional(BufferedInputStream inputStream) throws Exception {
        String result = null;
        try {
            result = simpleToTraditional(inputStream, CharsetDetector.getCharsetType(inputStream));
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * 对数据流进行简体转繁体
     *
     * @param inputStream  输入要转换的数据流
     * @param charset      指定要转换的数据流的字符集
     * @return 返回转换后的字符串
     **/
    public static String simpleToTraditional(InputStream inputStream, String charset) throws Exception {
        String result = null;
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            List<String> list = new ArrayList<>();
            read = new InputStreamReader(inputStream, charset);// 考虑到编码格式
            bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                list.add(simpleToTraditional(lineTxt));
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
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 对字符串进行简体转繁体
     *
     * @param original  输入要转换的字符串
     * @return 返回转换后的字符串
     **/
    public static String simpleToTraditional(String original) throws Exception {
        String result = null;
        try {
            result = ZhConverterUtil.convertToTraditional(original);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * 对文件进行繁体转简体
     *
     * @param file  输入要转换的文件
     * @return 返回转换后的字符串
     **/
    public static String traditionalToSimple(File file) throws Exception {
        String result = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            result = traditionalToSimple(inputStream, CharsetDetector.getCharsetType(file));
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            try {
                if (!Objects.isNull(inputStream)) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 对数据流进行繁体转简体
     *
     * @param inputStream  输入要转换的数据流
     * @return 返回转换后的字符串
     **/
    public static String traditionalToSimple(BufferedInputStream inputStream) throws Exception {
        String result = null;
        try {
            result = traditionalToSimple(inputStream, CharsetDetector.getCharsetType(inputStream));
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * 对数据流进行繁体转简体
     *
     * @param inputStream  输入要转换的数据流
     * @param charset      指定要转换的数据流的字符集
     * @return 返回转换后的字符串
     **/
    public static String traditionalToSimple(InputStream inputStream, String charset) throws Exception {
        String result = null;
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            List<String> list = new ArrayList<>();
            read = new InputStreamReader(inputStream, charset);// 考虑到编码格式
            bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                list.add(traditionalToSimple(lineTxt));
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
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 对字符串进行繁体转简体
     *
     * @param original  输入要转换的字符串
     * @return 返回转换后的字符串
     **/
    public static String traditionalToSimple(String original) throws Exception {
        String result = null;
        try {
            result = ZhConverterUtil.convertToSimple(original);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * 检测字符串是中文简体还是繁体
     *
     * @param original  输入检测的字符串
     * @return 返回检测的类型
     **/
    public static String detectChineseType(String original) throws Exception {
        String result = null;
        String temp = new String(original.getBytes(GB2312), GB2312);
        if (original.equals(temp)) {
            result = SIMPLE;
        } else {
            result = TRADITIONAL;
        }
        return result;
    }

    /**
     * 检测数据流是中文简体还是繁体
     *
     * @param inputStream  输入检测的字符流
     * @return 返回检测的类型
     **/
    public static String detectChineseType(InputStream inputStream) throws Exception {
        return detectChineseType(getOneLineText(inputStream, CharsetDetector.getCharsetType(new BufferedInputStream(inputStream))));
    }

    /**
     * 检测数据流是中文简体还是繁体
     *
     * @param inputStream  输入检测的数据流
     * @param charset      指定要检测的数据流的字符集
     * @return 返回检测的类型
     **/
    public static String detectChineseType(InputStream inputStream, String charset) throws Exception {
        return detectChineseType(getOneLineText(inputStream, charset));
    }

    /**
     * 检测文件的中的汉字是中文简体还是繁体
     *
     * @param file  要检测的文件
     * @return 返回检测的类型
     **/
    public static String detectChineseType(File file) throws Exception {
        String result = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            result = detectChineseType(getOneLineText(fileInputStream, CharsetDetector.getCharsetType(file)));
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
     * 检测文件的中的汉字是中文简体还是繁体
     *
     * @param file  要检测的文件
     * @param charset      指定要检测的文件的字符集
     * @return 返回检测的类型
     **/
    public static String detectChineseType(File file, String charset) throws Exception {
        String result = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            result = detectChineseType(getOneLineText(fileInputStream, charset));
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

    private static String getOneLineText(InputStream inputStream, String charset) throws Exception {
        String result = null;
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            read = new InputStreamReader(inputStream, charset);// 考虑到编码格式
            bufferedReader = new BufferedReader(read);
            result = bufferedReader.readLine();
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
                e.printStackTrace();
            }
        }
        return result;
    }
}
