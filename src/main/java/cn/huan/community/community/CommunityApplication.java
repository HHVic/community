package cn.huan.community.community;

import cn.huan.community.community.util.OSSClientUtil;
import com.aliyun.oss.OSSClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = {"cn.huan.community.community.mapper","cn.huan.community.community.dao"})
public class CommunityApplication {

    @Value("${oss.aliyun.endpoint}")
    private String endpoint;
    @Value("${oss.aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.aliyun.bucketName}")
    private String bucketName;
    @Value("${oss.aliyun.filedir}")
    private String filedir;

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

    @Bean
    public OSSClientUtil OSSClientUtil() {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        OSSClientUtil ossClientUtil = new OSSClientUtil(ossClient);
        ossClientUtil.setAccessKeyId(accessKeyId);
        ossClientUtil.setAccessKeySecret(accessKeySecret);
        ossClientUtil.setBucketName(bucketName);
        ossClientUtil.setEndpoint(endpoint);
        ossClientUtil.setFiledir(filedir);
        
        return ossClientUtil;
    }

}
