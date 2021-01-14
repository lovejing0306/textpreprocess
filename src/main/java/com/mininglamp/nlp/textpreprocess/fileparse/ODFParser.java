package com.mininglamp.nlp.textpreprocess.fileparse;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.odf.OpenDocumentParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * 解析odf
 **/
public class ODFParser extends Parser {
    public static String parser(File file) throws Exception {
        String result = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            result = ODFParser.parser(inputStream);
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
        String result = null;
        try {
            //detecting the file type
            BodyContentHandler handler = new BodyContentHandler(-1);
            Metadata metadata = new Metadata();
            ParseContext pcontext = new ParseContext();

            OpenDocumentParser openofficeparser = new OpenDocumentParser();
            openofficeparser.parse(inputStream, handler, metadata, pcontext);

            result = handler.toString();
        } catch (TikaException e) {
            throw e;
        } catch (SAXException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
        return result;
    }
}
