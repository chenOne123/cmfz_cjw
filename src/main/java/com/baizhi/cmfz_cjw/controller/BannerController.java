package com.baizhi.cmfz_cjw.controller;

import com.baizhi.cmfz_cjw.entity.Banner;
import com.baizhi.cmfz_cjw.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/getSelect")
    public Map getSelect(int page, int rows){
        System.out.println("进来了"+page+" "+rows);
        return bannerService.getAll(page,rows);
    }

    @RequestMapping("/getAdd")
    public Boolean getAdd(Banner banner, MultipartFile multipartFile, HttpServletRequest request){
        try {
            //获取文件路径
            String pash = request.getRealPath("/img");
            System.out.println(pash+"123");
            //获取文件的名字
            String fileName = multipartFile.getOriginalFilename();
            //给文件重命名
            String xname = new Date().getTime()+"_"+fileName;

            String s = pash.substring(pash.lastIndexOf("\\")+1);
            System.out.println(s+"111111");
            s+="/"+xname;
            System.out.println(s);

            //保存文件到指定路径
            File file = new File(pash+"/"+xname);
            multipartFile.transferTo(file);

            banner.setUrl(s);
            bannerService.getAdd(banner);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //更改
    @RequestMapping("/getSove")
    public Boolean getSove(int id, String status){
        System.out.println("进来了"+id+" "+status);
        return bannerService.getUpdate(id,status);
    }

    //删除
    @RequestMapping("/getDelete")
    public Boolean getDelete(int id){
        try {
            bannerService.getDelete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
