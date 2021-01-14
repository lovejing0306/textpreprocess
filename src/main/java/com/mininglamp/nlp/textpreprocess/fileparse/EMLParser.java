package com.mininglamp.nlp.textpreprocess.fileparse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.util.*;

/**
 * 解析邮件
 **/
public class EMLParser {
    private static final Logger LOGGER = Logger.getLogger(EMLParser.class);
    private static final Set<String> PARSETYPE = new HashSet<String>() {{
        add("doc");
        add("docx");
        add("html");
        add("htm");
        add("odp");
        add("pdf");
        add("ppt");
        add("pptx");
        add("rtf");
        add("txt");
        add("xls");
        add("xlsx");
        add("xml");
    }};

    /**
     * 解析文件类型的邮件
     *
     * @param file  输入要解析的文件
     * @return 以JSONObject对象的形式返回解析后的结果
     **/
    public static JSONObject parser(File file) {
        JSONObject result = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            result = parser(inputStream);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
     * 解析数据流类型的邮件
     *
     * @param inputStream  输入要解析的数据流
     * @return 以JSONObject对象的形式返回解析后的结果
     **/
    public static JSONObject  parser(InputStream inputStream) {
        JSONObject result = null;
        try {
            result = new JSONObject();
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
            Message msg = new MimeMessage(session, inputStream);
            Address[] froms = msg.getFrom();
            if (!Objects.isNull(froms)) {
                InternetAddress address = (InternetAddress) froms[0];
                result.put("sender", address.getAddress());
            }
            result.put("theme", msg.getSubject());
            result.put("attach_files", new JSONArray());

            // getContent() 是获取包裹内容, Part相当于外包装
            Object o = msg.getContent();
            if (o instanceof Multipart) {
                Multipart multipart = (Multipart) o;
                result = reMultipart(multipart, result);
            } else if (o instanceof Part) {
                Part part = (Part) o;
                result = rePart(part, result);
            } else {
                result.put("content_type", msg.getContentType());
                result.put("content", msg.getContent());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    private static JSONObject rePart(Part part, JSONObject jsonObject) throws Exception {
        if (Objects.isNull(part.getDisposition())) {
            if (part.getContentType().startsWith("text/plain")) {
                jsonObject.put("content", part.getContent());
            }
        } else {
            String fileName = part.getFileName();
            if (!StringUtils.isEmpty(fileName)) {
                // MimeUtility.decodeText解决附件名乱码问题
                fileName = MimeUtility.decodeText(fileName);
                int lastIndexOfPoint = fileName.lastIndexOf(".");
                if (lastIndexOfPoint == -1) {
                    throw new Exception("解析文件无后缀名！");
                } else {
                    JSONObject attachFile = new JSONObject();
                    attachFile.put("file_name", fileName);
                    String fileType = fileName.substring(lastIndexOfPoint + 1);
                    InputStream in = part.getInputStream();// 打开附件的输入流
                    if (PARSETYPE.contains(fileType)) {
                        attachFile.put("content", Parser.parser(in));
                        jsonObject.getJSONArray("attach_files").put(attachFile);
                    } else {
                        throw new Exception("此类文件无法解析！");
                    }
                }

            }
        }
        return jsonObject;
    }


    private static JSONObject reMultipart(Multipart multipart, JSONObject jsonObject) {
        try {
            // 依次处理各个部分
            for (int j = 0, n = multipart.getCount(); j < n; j++) {
                try {
                    Part part = multipart.getBodyPart(j);// 解包, 取出 MultiPart的各个部分,
                    if (part.getContent() instanceof Multipart) {
                        Multipart p = (Multipart) part.getContent();// 转成小包裹
                        // 递归迭代
                        jsonObject = reMultipart(p, jsonObject);
                    } else {
                        jsonObject = rePart(part, jsonObject);
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return jsonObject;
    }

}
