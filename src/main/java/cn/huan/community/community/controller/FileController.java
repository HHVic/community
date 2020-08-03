package cn.huan.community.community.controller;

import cn.huan.community.community.dto.FileDTO;
import cn.huan.community.community.util.OSSClientUtil;
import com.aliyun.oss.OSSClient;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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
    public FileDTO upload(@RequestParam("editormd-image-file")MultipartFile multipartFile) throws Exception {

        InputStream is = multipartFile.getInputStream();
        String name = ossClientUtil.uploadFile2OSS(is,multipartFile.getOriginalFilename());
        String imgUrl = ossClientUtil.getImgUrl(multipartFile.getOriginalFilename());
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setMessage("上传成功");
        fileDTO.setUrl(imgUrl);
        return fileDTO;
    }
}
