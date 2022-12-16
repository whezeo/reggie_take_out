package com.cuit.reggie.controller;


import com.cuit.reggie.vo.R;
import com.cuit.reggie.vo.dto.DishDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @author liveb
 */
@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {
    @Value("${reggie.path}")
    private String filePath;
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        log.info(filePath.toString());
        File dir = new File(filePath);
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String name = UUID.randomUUID().toString() + suffix;
        if(!dir.exists()){
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(filePath +File.separator + name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(name);
    }

    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try {
            FileInputStream fis = new FileInputStream(filePath +File.separator+ name);
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");

            byte[] bytes = new byte[1024];
            int length=0;
            while((length=fis.read(bytes))!=-1){
                outputStream.write(bytes,0,length);
                outputStream.flush();
            }

            outputStream.close();
            fis.close();
        } catch (IOException e) {
           log.info("文件未找到 ");
        }
    }
}
