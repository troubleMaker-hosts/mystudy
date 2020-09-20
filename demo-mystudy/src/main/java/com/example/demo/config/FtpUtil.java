package com.example.demo.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.io.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: FtpConfig
 * @Description: ftp 配置(工具) 类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/09/03 03:08
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public class FtpUtil {
    /**
     * 日志(log4j)
     */
    private static final Logger LOGGER = LogManager.getLogger(FtpUtil.class);

    /**
     * 获取 系统名 :
     * linux :	    Linux
     * windows :	Windows XP
     */
    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static final String PATH_SEPARATOR = "/";

    /**
     * ftpBasePath 初始化时ftp服务器路径(默认路径)
     */
    public static final String FTP_BASE_PATH = "/";

    /**
     * 默认 配置 ftp 字符集
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 默认 配置 ftp 超时时间
     * 毫秒(millisecond) (second * 1000)
     */
    private static final int DEFAULT_TIMEOUT = 6 * 1000;


    /**
     * ftpClient对象
     */
    private FTPClient ftpClient;


    /**
     * 默认 配置 ftp 初始化时ftp服务器路径(根目录)
     */
    private String ftpBasePath;

    /**
     * 默认 配置 ftp 主机名(ip)
     */
    private String hostname;

    /**
     * 默认 配置 ftp 端口号
     */
    private Integer port;

    /**
     * 默认 配置 ftp 用户名
     */
    private String username;

    /**
     * 默认 配置 ftp 密码
     */
    private String password;


    /**
     * 私有构造函数
     *
     * @param host        主机名(ip)
     * @param port        端口号
     * @param username    用户名
     * @param password    密码
     * @param charset     字符编码
     * @param ftpBasePath 初始化时ftp服务器路径(根目录)
     */
    private FtpUtil(String host, int port, String username, String password, String charset, String ftpBasePath) {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding(Strings.isEmpty(charset) ? DEFAULT_CHARSET : charset);
        this.hostname = StringUtils.isEmpty(host) ? "127.0.0.1" : host;
        this.port = (port <= 0) ? 21 : port;
        this.username = StringUtils.isEmpty(username) ? "anonymous" : username;
        this.password = password;
        this.ftpBasePath = Strings.isEmpty(ftpBasePath) ? DEFAULT_CHARSET : ftpBasePath;
        setTimeout(DEFAULT_TIMEOUT, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
    }

    /**
     * 创建默认的FtpClient
     *
     * @param host        主机名(ip)
     * @param port        端口号
     * @param username    用户名
     * @param password    密码
     * @param ftpBasePath 初始化时ftp服务器路径(根目录)
     * @return  FtpUtil
     */
    public static FtpUtil createFtpClient(String host, int port, String username, String password, String ftpBasePath) {
        return new FtpUtil(host, port, username, password, DEFAULT_CHARSET, ftpBasePath);
    }

    /**
     * 创建的FtpClient
     *
     * @param host        主机名(ip)
     * @param port        端口号
     * @param username    用户名
     * @param password    密码
     * @param charset     字符编码
     * @param ftpBasePath 初始化时ftp服务器路径(根目录)
     * @return FtpUtil
     */
    public static FtpUtil createFtpClient(String host, int port, String username, String password, String charset, String ftpBasePath) {
        return new FtpUtil(host, port, username, password, charset, ftpBasePath);
    }

    /**
     * 设置(默认)超时时间
     */
    private void setTimeout() {
        setTimeout(DEFAULT_TIMEOUT, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
    }

    /**
     * 设置超时时间
     *
     * @param defaultTimeout 超时时间(打开套接字时使用的默认超时)
     * @param connectTimeout 连接超时时间
     * @param dataTimeout    超时时间(数据连接读取时使用的超时)
     */
    private void setTimeout(int defaultTimeout, int connectTimeout, int dataTimeout) {
        ftpClient.setDefaultTimeout(defaultTimeout);
        ftpClient.setConnectTimeout(connectTimeout);
        ftpClient.setDataTimeout(dataTimeout);
    }

    /**
     * 连接到ftp
     */
    public void connect() throws IOException {
        try {
            ftpClient.connect(hostname, port);
        } catch (UnknownHostException e) {
            throw new IOException("Can't find FTP server :" + hostname);
        }

        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            disconnect();
            throw new IOException("Can't connect to server :" + hostname);
        }

        if (!ftpClient.login(username, password)) {
            disconnect();
            throw new IOException("Can't login to server :" + hostname);
        }

        // set data transfer mode.
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        // Use passive mode to pass firewalls.
        ftpClient.enterLocalPassiveMode();

        initFtpBasePath();
    }

    /**
     * 连接ftp时保存刚登陆ftp时的路径
     */
    private void initFtpBasePath() throws IOException {
        if (StringUtils.isEmpty(ftpBasePath)) {
            synchronized (this) {
                if (StringUtils.isEmpty(ftpBasePath)) {
                    ftpBasePath = ftpClient.printWorkingDirectory();
                }
            }
        }
    }

    /**
     * ftp是否处于连接状态，是连接状态返回<tt>true</tt>
     *
     * @return boolean  ftp连接状态
     */
    public boolean isConnected() {
        return ftpClient.isConnected();
    }

    /**
     * 上传文件到对应目录下
     *
     * @param fileName    文件名
     * @param inputStream 文件输入流
     * @param uploadDir   上传文件的父路径
     * @return java.lang.String
     */
    public String uploadFile(String fileName, InputStream inputStream, String uploadDir) throws IOException {
        changeWorkingDirectory(ftpBasePath);
        makeDirs(uploadDir);
        storeFile(fileName, inputStream);
        return uploadDir + "/" + fileName;
    }

    /**
     * 根据uploadFile返回的路径，从ftp下载文件到指定输出流中
     *
     * @param ftpRealFilePath 方法uploadFile返回的路径
     * @param outputStream    输出流
     */
    public void downloadFileFromDailyDir(String ftpRealFilePath, OutputStream outputStream) throws IOException {
        changeWorkingDirectory(ftpBasePath);
        ftpClient.retrieveFile(ftpRealFilePath, outputStream);
    }

    /**
     * 获取ftp上指定文件名到输出流中
     *
     * @param ftpFileName 文件在ftp上的路径  如绝对路径 /home/ftpuser/123.txt 或者相对路径 123.txt
     * @param out         输出流
     */
    public void retrieveFile(String ftpFileName, OutputStream out) throws IOException {
        try {
            FTPFile[] fileInfoArray = ftpClient.listFiles(ftpFileName);
            if (fileInfoArray == null || fileInfoArray.length == 0) {
                throw new FileNotFoundException("File '" + ftpFileName + "' was not found on FTP server.");
            }

            FTPFile fileInfo = fileInfoArray[0];
            if (fileInfo.getSize() > Integer.MAX_VALUE) {
                throw new IOException("File '" + ftpFileName + "' is too large.");
            }

            if (!ftpClient.retrieveFile(ftpFileName, out)) {
                throw new IOException("Error loading file '" + ftpFileName + "' from FTP server. Check FTP permissions and path.");
            }
            out.flush();
        } finally {
            closeStream(out);
        }
    }


    /**
     * 将输入流存储到指定的ftp路径下
     *
     * @param ftpFileName 文件在ftp上的路径 如绝对路径 /home/ftpuser/123.txt 或者相对路径 123.txt
     * @param in          输入流
     */
    private void storeFile(String ftpFileName, InputStream in) throws IOException {
        try {
            if (!ftpClient.storeFile(ftpFileName, in)) {
                throw new IOException("Can't upload file '" + ftpFileName + "' to FTP server. Check FTP permissions and path.");
            }
        } finally {
            closeStream(in);
        }
    }

    /**
     * 根据文件ftp路径名称删除文件
     *
     * @param ftpFileName 文件ftp路径名称
     */
    public void deleteFile(String ftpFileName) throws IOException {
        if (!ftpClient.deleteFile(ftpFileName)) {
            throw new IOException("Can't remove file '" + ftpFileName + "' from FTP server.");
        }
    }

    /**
     * 上传文件到ftp
     *
     * @param ftpFileName 上传到ftp文件路径名称
     * @param localFile   本地文件路径名称
     */
    public void upload(String ftpFileName, File localFile) throws IOException {
        if (!localFile.exists()) {
            throw new IOException("Can't upload '" + localFile.getAbsolutePath() + "'. This file doesn't exist.");
        }

        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(localFile));
            if (!ftpClient.storeFile(ftpFileName, in)) {
                throw new IOException("Can't upload file '" + ftpFileName + "' to FTP server. Check FTP permissions and path.");
            }
        } finally {
            closeStream(in);
        }
    }

    /**
     * 上传文件夹到ftp上
     *
     * @param remotePath ftp上文件夹路径名称
     * @param localPath  本地上传的文件夹路径名称
     */
    public void uploadDir(String remotePath, String localPath) throws IOException {
        localPath = localPath.replace("\\\\", "/");
        File file = new File(localPath);
        if (file.exists()) {
            if (!ftpClient.changeWorkingDirectory(remotePath)) {
                ftpClient.makeDirectory(remotePath);
                ftpClient.changeWorkingDirectory(remotePath);
            }
            File[] files = file.listFiles();
            if (null != files) {
                for (File f : files) {
                    if (f.isDirectory() && !f.getName().equals(".") && !f.getName().equals("..")) {
                        uploadDir(remotePath + "/" + f.getName(), f.getPath());
                    } else if (f.isFile()) {
                        upload(remotePath + "/" + f.getName(), f);
                    }
                }
            }
        }
    }

    /**
     * 下载ftp文件到本地上
     *
     * @param ftpFileName ftp文件路径名称
     * @param localFile   本地文件路径名称
     */
    public void download(String ftpFileName, File localFile) throws IOException {
        OutputStream out = null;
        try {
            FTPFile[] fileInfoArray = ftpClient.listFiles(ftpFileName);
            if (fileInfoArray == null || fileInfoArray.length == 0) {
                throw new FileNotFoundException("File " + ftpFileName + " was not found on FTP server.");
            }

            FTPFile fileInfo = fileInfoArray[0];
            if (fileInfo.getSize() > Integer.MAX_VALUE) {
                throw new IOException("File " + ftpFileName + " is too large.");
            }

            out = new BufferedOutputStream(new FileOutputStream(localFile));
            if (!ftpClient.retrieveFile(ftpFileName, out)) {
                throw new IOException("Error loading file " + ftpFileName + " from FTP server. Check FTP permissions and path.");
            }
            out.flush();
        } finally {
            closeStream(out);
        }
    }


    /**
     * 改变工作目录
     *
     * @param dir ftp服务器上目录
     * @return boolean 改变成功返回true
     */
    public boolean changeWorkingDirectory(String dir) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {
            return ftpClient.changeWorkingDirectory(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 从ftp服务器上下载文件夹到本地
     *
     * @param remotePath ftp上文件夹路径名称
     * @param localPath  本地上传的文件夹路径名称
     */
    public void downloadDir(String remotePath, String localPath) throws IOException {
        localPath = localPath.replace("\\\\", "/");
        File file = new File(localPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FTPFile[] ftpFiles = ftpClient.listFiles(remotePath);
        for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) {
            FTPFile ftpFile = ftpFiles[i];
            if (ftpFile.isDirectory() && !ftpFile.getName().equals(".") && !ftpFile.getName().equals("..")) {
                downloadDir(remotePath + "/" + ftpFile.getName(), localPath + "/" + ftpFile.getName());
            } else {
                download(remotePath + "/" + ftpFile.getName(), new File(localPath + "/" + ftpFile.getName()));
            }
        }
    }


    /**
     * 列出ftp上文件目录下的文件
     *
     * @param filePath ftp上文件目录
     * @return java.util.List<java.lang.String>
     */
    public List<String> listFileNames(String filePath) throws IOException {
        FTPFile[] ftpFiles = ftpClient.listFiles(filePath);
        List<String> fileList = new ArrayList<>();
        if (ftpFiles != null) {
            for (int i = 0; i < ftpFiles.length; i++) {
                FTPFile ftpFile = ftpFiles[i];
                if (ftpFile.isFile()) {
                    fileList.add(ftpFile.getName());
                }
            }
        }

        return fileList;
    }

    /**
     * 发送ftp命令到ftp服务器中
     *
     * @param args ftp命令
     */
    public void sendSiteCommand(String args) throws IOException {
        if (!ftpClient.isConnected()) {
            ftpClient.sendSiteCommand(args);
        }
    }

    /**
     * 获取当前所处的工作目录
     *
     * @return java.lang.String 当前所处的工作目录
     */
    public String printWorkingDirectory() {
        if (!ftpClient.isConnected()) {
            return "";
        }
        try {
            return ftpClient.printWorkingDirectory();
        } catch (IOException e) {
            // do nothing
        }

        return "";
    }

    /**
     * 切换到当前工作目录的父目录下
     *
     * @return boolean 切换成功返回true
     */
    public boolean changeToParentDirectory() {

        if (!ftpClient.isConnected()) {
            return false;
        }

        try {
            return ftpClient.changeToParentDirectory();
        } catch (IOException e) {
            // do nothing
        }

        return false;
    }

    /**
     * 返回当前工作目录的上一级目录
     *
     * @return java.lang.String 当前工作目录的父目录
     */
    public String printParentDirectory() {
        if (!ftpClient.isConnected()) {
            return "";
        }

        String w = printWorkingDirectory();
        changeToParentDirectory();
        String p = printWorkingDirectory();
        changeWorkingDirectory(w);

        return p;
    }

    /**
     * 创建目录
     *
     * @param pathname 路径名
     * @return boolean 创建成功返回true
     */
    public boolean makeDirectory(String pathname) throws IOException {
        return ftpClient.makeDirectory(pathname);
    }

    /**
     * 创建多个目录
     *
     * @param pathname 路径名
     */
    public void makeDirs(String pathname) throws IOException {
        pathname = pathname.replace("\\\\", "/");
        String[] pathnameArray = pathname.split("/");
        for (String each : pathnameArray) {
            if (StringUtils.isNotEmpty(each)) {
                ftpClient.makeDirectory(each);
                ftpClient.changeWorkingDirectory(each);
            }
        }
    }

    /**
     * 关闭流
     *
     * @param stream 流
     */
    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException ex) {
                // do nothing
            }
        }
    }

    /**
     * 关闭ftp连接
     */
    public void disconnect() {
        if (null != ftpClient && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException ex) {
                // do nothing
            }
        }
    }
}
