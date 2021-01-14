package com.mininglamp.nlp.textpreprocess.charsetdetector;

import com.ibm.icu.text.CharsetMatch;
import junit.framework.TestCase;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test extends TestCase {

    public void testDetector() throws Exception{
        String filePath = "/Users/littlebei/Program/mininglamp/java/textpreprocess/data/test/charsetconverter.gb18030";
        System.out.println(CharsetDetector.getCharsetType(new File(filePath)));
        System.out.println(CharsetDetector.getCharsetTypeByCD(new File(filePath)));
        System.out.println(CharsetDetector.getCharsetTypeByICU(new File(filePath)));
    }
}
