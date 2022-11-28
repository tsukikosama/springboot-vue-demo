package com.miku.comtroller;

import cn.hutool.Hutool;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.miku.common.Result;
import com.miku.entity.Image;
import com.miku.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    private static  final  String imgUrl = "G:\\a_Java\\blogs\\src\\main\\resources\\static";
    @Autowired
    private ImageService service;

    @GetMapping("/getimg")
    public Result getImg(){
        return Result.ok(service.list());
    }

    @PostMapping("")
    public Result upload(@RequestParam("file") MultipartFile image){

//        System.out.println(image);
        //获取保存的图片的名字
//        String imgName = image.getName();
        //
//        Image image1 = new Image()
        String originalFilename = image.getOriginalFilename();
        String str = preFixName(originalFilename);
        System.out.println(str);
        try {
            //把文件存入指定的地址
            image.transferTo(new File(imgUrl,str));
            System.out.println("http://localhost:8080//"+str);
            Image image1 = new Image(str,"http://localhost:8080//"+str);
            boolean success = service.save(image1);
            if(!success){
                return Result.fail("添加失败");
            }
            System.out.println("上传成功");
        }catch (Exception e){
            log.warn(e.toString());
        }

        return Result.ok();
    }

    /**
     * 获取随机文件名
     * @param filename
     * @return
     */
    public String preFixName(String filename){
        // 获取后缀
        String suffix = StrUtil.subAfter(filename, ".", true);
        filename = "cc" + UUID.randomUUID() + "."+ suffix;
        return filename;
    }
}
