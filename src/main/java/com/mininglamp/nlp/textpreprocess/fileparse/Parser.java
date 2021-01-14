package com.mininglamp.nlp.textpreprocess.fileparse;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Parser {
    private static Pattern pattern = Pattern.compile("\\\\s*|\\t|\\r|\\n");

    private static String removeChar(String content) throws Exception {
        String[] lines = content.split("\\n+");
        List<String> list = new ArrayList<>();
        for (String line : lines) {
            line = line.trim();
            line = pattern.matcher(line).replaceAll("");
            if (Objects.isNull(line) || line.equals("") || line.length() == 0) {
                continue;
            }
            list.add(line);
        }
        return String.join("\n", list);
    }

    public static String parser(File file) throws Exception {
        String result = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            result = parser(inputStream);
        } catch (Exception e) {
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

    public static String parser(InputStream inputStream) throws Exception {
        //detecting the file type
        BodyContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        ParseContext pcontext = new ParseContext();

        org.apache.tika.parser.Parser parser = new AutoDetectParser();
        parser.parse(inputStream, handler, metadata, pcontext);
        // String content = handler.toString();
        // return removeChar(content);
        return handler.toString();
    }

}
