package com.mininglamp.nlp.textpreprocess.filedecompress;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileDecompress {

    /**
     * 获取文件真实类型
     *
     * @param file 要获取类型的文件。
     * @return 文件类型枚举。
     */
    private static FileType getFileType(File file) throws Exception {
        FileType fileType = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            fileType = getFileType(inputStream);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileType;
    }

    private static FileType getFileType(InputStream inputStream) throws Exception {
        try {
            byte[] head = new byte[4];
            if (-1 == inputStream.read(head)) {
                return FileType.UNKNOWN;
            }
            int headHex = 0;
            for (byte b : head) {
                headHex <<= 8;
                headHex |= b;
            }
            switch (headHex) {
                case 0x504B0304:
                    return FileType.ZIP;
                case 0x776f7264:
                    return FileType.TAR;
                case -0x51:
                    return FileType._7Z;
                case 0x425a6839:
                    return FileType.BZ2;
                case -0x74f7f8:
                    return FileType.GZ;
                case 0x52617221:
                    return FileType.RAR;
                default:
                    return FileType.UNKNOWN;
            }
        } catch (IOException e) {
            throw e;
        }
        //return FileType.UNKNOWN;
    }

    /**
     * 构建目录
     *
     * @param outputDir 输出目录
     * @param subDir    子目录
     */
    private static void createDirectory(String outputDir, String subDir) throws Exception {
        File file = new File(outputDir);
        if (!(subDir == null || subDir.trim().equals(""))) {//子目录不为空
            file = new File(outputDir + File.separator + subDir);
        }
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.mkdirs();
        }
    }

    /**
     * 解压缩tar文件
     *
     * @param file       压缩包文件
     * @param targetPath 目标文件夹
     */
    public static void decompressTar(File file, String targetPath) throws Exception {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            decompressTar(inputStream, targetPath);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void decompressTar(InputStream inputStream, String targetPath) throws Exception {
        OutputStream fos = null;
        TarInputStream tarInputStream = null;
        try {
            tarInputStream = new TarInputStream(inputStream, 1024 * 2);
            // 创建输出目录
            createDirectory(targetPath, null);

            TarEntry entry = null;
            while (true) {
                entry = tarInputStream.getNextEntry();
                if (entry == null) {
                    break;
                }
                if (entry.isDirectory()) {
                    createDirectory(targetPath, entry.getName()); // 创建子目录
                } else {
                    fos = new FileOutputStream(new File(targetPath + File.separator + entry.getName()));
                    int count;
                    byte data[] = new byte[2048];
                    while ((count = tarInputStream.read(data)) != -1) {
                        fos.write(data, 0, count);
                    }
                    fos.flush();
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (tarInputStream != null) {
                    tarInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压缩tar.gz文件
     *
     * @param file       压缩包文件
     * @param targetPath 目标文件夹
     */
    public static void decompressTarGz(File file, String targetPath) throws Exception {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            decompressTarGz(inputStream, targetPath);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void decompressTarGz(InputStream inputStream, String targetPath) throws Exception {
        BufferedInputStream bufferedInputStream = null;
        GZIPInputStream gzipIn = null;
        TarInputStream tarIn = null;
        OutputStream out = null;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            gzipIn = new GZIPInputStream(bufferedInputStream);
            tarIn = new TarInputStream(gzipIn, 1024 * 2);

            // 创建输出目录
            createDirectory(targetPath, null);

            TarEntry entry = null;
            while ((entry = tarIn.getNextEntry()) != null) {
                if (entry.isDirectory()) { // 是目录
                    createDirectory(targetPath, entry.getName()); // 创建子目录
                } else { // 是文件
                    File tempFIle = new File(targetPath + File.separator + entry.getName());
                    createDirectory(tempFIle.getParent() + File.separator, null);
                    out = new FileOutputStream(tempFIle);
                    int len = 0;
                    byte[] b = new byte[2048];

                    while ((len = tarIn.read(b)) != -1) {
                        out.write(b, 0, len);
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (tarIn != null) {
                    tarIn.close();
                }
                if (gzipIn != null) {
                    gzipIn.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压缩gz文件
     *
     * @param file       压缩包文件
     * @param targetPath 目标文件夹
     */
    public static void decompressGz(File file, String targetPath) throws Exception {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            decompressGz(fileInputStream, targetPath);
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void decompressGz(InputStream fileInputStream, String targetPath) throws Exception {
        GZIPInputStream gzipIn = null;
        OutputStream out = null;
        //String suffix = ".gz";
        try {
            gzipIn = new GZIPInputStream(fileInputStream);
            // 创建输出目录
            createDirectory(targetPath, null);
            // file.getName().replace(suffix, "")
            File tempFile = new File(targetPath + File.separator + String.valueOf(System.currentTimeMillis()));
            out = new FileOutputStream(tempFile);
            int count;
            byte data[] = new byte[2048];
            while ((count = gzipIn.read(data)) != -1) {
                out.write(data, 0, count);
            }
            out.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (gzipIn != null) {
                    gzipIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压缩7z文件
     *
     * @param file       压缩包文件
     * @param targetPath 目标文件夹
     */
    public static void decompress7Z(File file, String targetPath, String password) throws Exception {
        SevenZFile sevenZFile = null;
        OutputStream outputStream = null;
        try {
            if(Objects.isNull(password) || password.equals("")){
                sevenZFile = new SevenZFile(file);
            }else{
                sevenZFile = new SevenZFile(file, password.toCharArray());
            }
            // 创建输出目录
            createDirectory(targetPath, null);
            SevenZArchiveEntry entry;

            while ((entry = sevenZFile.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    createDirectory(targetPath, entry.getName()); // 创建子目录
                } else {
                    outputStream = new FileOutputStream(new File(targetPath + File.separator + entry.getName()));
                    int len = 0;
                    byte[] b = new byte[2048];
                    while ((len = sevenZFile.read(b)) != -1) {
                        outputStream.write(b, 0, len);
                    }
                    outputStream.flush();
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (sevenZFile != null) {
                    sevenZFile.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压缩RAR文件
     *
     * @param file       压缩包文件
     * @param targetPath 目标文件夹
     */
    public static void decompressRAR(File file, String targetPath) throws Exception {
        Archive archive = null;
        OutputStream outputStream = null;
        try {
            archive = new Archive(file);
            FileHeader fileHeader;
            // 创建输出目录
            createDirectory(targetPath, null);
            while ((fileHeader = archive.nextFileHeader()) != null) {
                if (fileHeader.isDirectory()) {
                    createDirectory(targetPath, fileHeader.getFileNameString().trim()); // 创建子目录
                } else {
                    outputStream = new FileOutputStream(new File(targetPath + File.separator + fileHeader.getFileNameString().trim()));
                    archive.extractFile(fileHeader, outputStream);
                }
            }
        } catch (RarException | IOException e) {
            throw e;
        } finally {
            try {
                if (archive != null) {
                    archive.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 解压缩zipFile
     *
     * @param inputStream  要解压的zip字节流对象
     * @param targetPath   要解压到某个指定的目录下
     * @throws IOException
     */
    public static void decompressZip(InputStream inputStream, String targetPath) throws Exception {
        decompressZip(inputStream, targetPath, "UTF-8");
    }

    public static void decompressZip(InputStream inputStream, String targetPath, String charsetName) throws Exception {
        BufferedInputStream bis = null;
        ZipInputStream zis = null;
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            zis = new ZipInputStream(inputStream, Charset.forName(charsetName));
            bis = new BufferedInputStream(zis);

            // 创建输出目录
            createDirectory(targetPath, null);

            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    createDirectory(targetPath, entry.getName()); // 创建子目录
                } else {
                    File tempFile = new File(targetPath + File.separator + entry.getName());
                    createDirectory(tempFile.getParent() + File.separator, null);
                    fileOutputStream = new FileOutputStream(tempFile);
                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

                    int b;
                    while ((b = bis.read()) != -1) {
                        bufferedOutputStream.write(b);
                    }
                    bufferedOutputStream.flush();
                    fileOutputStream.flush();
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (zis != null) {
                    zis.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void decompressZip(File file, String targetPath, String password) throws Exception{
        decompressZip(file, targetPath, password, "UTF-8");
    }

    public static void decompressZip(File file, String targetPath, String password, String charsetName) throws Exception{
        //1.判断指定目录是否存在
        File destDir = new File(targetPath);
        if (destDir.isDirectory() && !destDir.exists()) {
            destDir.mkdir();
        }
        //2.初始化zip工具
        ZipFile zFile = new ZipFile(file);
        zFile.setFileNameCharset(charsetName);
        if (!zFile.isValidZipFile()) {
            throw new ZipException("压缩文件不合法,可能被损坏.");
        }
        //3.判断是否已加密
        if (zFile.isEncrypted()) {
            if(Objects.isNull(password) || password.equals("")){
                throw new ZipException("加密文件需要密码！");
            }
            zFile.setPassword(password.toCharArray());
        }
        //4.解压所有文件
        zFile.extractAll(targetPath);
    }
}
