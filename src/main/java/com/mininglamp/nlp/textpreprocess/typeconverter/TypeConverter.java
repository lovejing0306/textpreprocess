package com.mininglamp.nlp.textpreprocess.typeconverter;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TypeConverter {
    public static InputStream stringToInputStream(String original, String encoding) throws Exception {
        ByteArrayInputStream result = null;
        try {
            if (original != null) {
                result = new ByteArrayInputStream(original.getBytes(encoding));
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public static String inputStreamToString(InputStream inputStream) throws Exception {
        String result = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = inputStreamToByteArrayOutputStream(inputStream);
            result = byteArrayOutputStream.toString(StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw e;
        } finally {
            try {
                if (!Objects.isNull(byteArrayOutputStream)) {
                    byteArrayOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String inputStreamToString(InputStream inputStream, String encoding) throws Exception {
        String result = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = inputStreamToByteArrayOutputStream(inputStream);
            result = byteArrayOutputStream.toString(encoding);
        } catch (UnsupportedEncodingException e) {
            throw e;
        } finally {
            try {
                if (!Objects.isNull(byteArrayOutputStream)) {
                    byteArrayOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static ByteArrayOutputStream inputStreamToByteArrayOutputStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream result = null;
        try {
            result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw e;
        }
        return result;
    }
}
