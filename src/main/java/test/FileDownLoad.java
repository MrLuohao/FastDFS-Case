package test;

import org.csource.fastdfs.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Title: FileDownLoad
 * @Author Mr.罗
 * @Package test
 * @Date 2023/11/20 18:54
 * @description: 文件下载
 */
public class FileDownLoad {
    public static void main(String[] args) {
        TrackerServer trackerServer = null;
        FileOutputStream fileOutputStream = null;
        try {
            // 加载配置文件
            ClientGlobal.initByProperties("config/fastdfs-client.properties");

            //创建tracker客户端
            TrackerClient trackerClient = new TrackerClient();
            //通过tracker客户端获取tracker的连接服务并返回
            trackerServer = trackerClient.getConnection();
            //声明storage服务
            StorageServer storageServer = null;
            //定义storage客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            //文件下载
            byte[] bytes = client.download_file1("group1/M00/00/00/wKj3gGVbN1SAJftmAAUU7QGZOds074.jpg");
            //通过io流将字节数组转换成文件
            fileOutputStream = new FileOutputStream(new File("D:\\FastDfsCase\\img\\DownLoad1.jpg"));
            fileOutputStream.write(bytes);
            System.out.println("文件下载成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //释放服务器资源和文件输出流
                trackerServer.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
