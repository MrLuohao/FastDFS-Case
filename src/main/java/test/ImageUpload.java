package test;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

/**
 * @Title: Upload
 * @Author Mr.罗
 * @Package test
 * @Date 2023/11/20 17:33
 * @description: FastDFS文件上传测试
 */
public class ImageUpload {
    public static void main(String[] args) {
        try {
            // 加载配置文件
            ClientGlobal.initByProperties("config/fastdfs-client.properties");

            //创建tracker客户端
            TrackerClient trackerClient = new TrackerClient();
            //通过tracker客户端获取tracker的连接服务并返回
            TrackerServer trackerServer = trackerClient.getConnection();
            //声明storage服务
            StorageServer storageServer = null;
            //定义storage客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            //定义文件元信息
            NameValuePair[] list = new NameValuePair[1];
            list[0]=new NameValuePair("fileName","1.jpg");

            //上传文件
            String fileID = client.upload_file1("D:\\FastDfsCase\\img\\1.jpg", "jpg", list);

            /*
            上传成功:group1/M00/00/00/wKj3gGVbN1SAJftmAAUU7QGZOds074.jpg
            group1:一台服务器就是一个组
            MOO: 配置文件中所配置的store_path0------->/home/fastdfs/fdfs_storage/data
            00/00:两级目录
            可以根据路径信息找到对应的文件
             */
            System.out.println("fileID:"+fileID);

            //释放服务器资源
            trackerServer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
