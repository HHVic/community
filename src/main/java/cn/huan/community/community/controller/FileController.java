package cn.huan.community.community.controller;

import cn.huan.community.community.dto.FileDTO;
import cn.huan.community.community.util.OSSClientUtil;
import com.aliyun.oss.OSSClient;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private OSSClientUtil ossClientUtil;

    @RequestMapping("/upload")
    @ResponseBody
    public FileDTO upload(@RequestParam("guid")String guid) throws FileNotFoundException {

        File file = new File("D:\\Desktop\\图片.png");
        InputStream is = new FileInputStream(file);
        String name = ossClientUtil.uploadFile2OSS(is,file.getName());
        String imgUrl = ossClientUtil.getImgUrl(file.getName());
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setMessage("上传成功");
        fileDTO.setUrl(imgUrl);
        return fileDTO;
    }
}
