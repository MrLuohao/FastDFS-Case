package test;

import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * @Title: FileQuery
 * @Author Mr.罗
 * @Package test
 * @Date 2023/11/20 18:41
 * @description: 文件的查询操作
 */
public class FileQuery {
    public static void main(String[] args) {
        TrackerServer trackerServer = null;
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

            //根据文件路径查询对应文件
            //fileInfo:source_ip_addr = 192.168.247.128, file_size = 333037, create_timestamp = 2023-11-20 18:39:16, crc32 = 26819035
            FileInfo fileInfo = client.query_file_info1("group1/M00/00/00/wKj3gGVbN1SAJftmAAUU7QGZOds074.jpg");//文件存在打印文件的相关信息，文件不存则返回null
            if (fileInfo != null) {
                System.out.println("fileInfo:" + fileInfo);
            }else {
                System.out.println("查无此文件");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //释放服务器资源
                trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
