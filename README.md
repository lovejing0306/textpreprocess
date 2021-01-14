# 文本处理
这是一个 Java 编写的文本处理小工具，主要功能包括：
* 简繁体转换
* 文件解析
* 文件字符集转换
* 文件解压

## 简繁体转换
1. 支持输入的类型：字符串、文件以及流作为输入
2. 性能：3M/s

## 文件解析
1. 支持解析的文件：doc、docx、ppt、pptx、pdf、rtf、xls、xlsx、html、xml、eml
2. 支持输入的类型：文件、字节流
3. 性能：
   1. pdf：6M/s
   2. doc：12M/s
   3. docx：7M/s
   4. xlxs：2M/s

## 文件字符集转换
1. 支持的字符集：gb18030、gb2312、gbk、unicode、big5、utf-8
2. 支持输入的类型：文件、字节流
3. 性能：11M~15M/s

## 文件解压
1. 支持解压的类型：zip、tar、gz.tar、gz、7z、rar
2. 支持输入的类型：
   1. zip、tar、gz.tar、gz支持以文件和字节流作为输入
   2. 7z、rar仅支持以文件作为输入
3. 性能：
   1. zip：9M/s
   2. tar：150M/s
   3. tar.gz：36M/s
   4. gz：80M/s
   5. 7z：30M/s
4. 特别说明
   1. 解压rar仅支持以rar-5.0以下版本压缩成文件