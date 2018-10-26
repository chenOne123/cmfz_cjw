package com.baizhi.cmfz_cjw.controller;

import com.baizhi.cmfz_cjw.entity.Album;
import com.baizhi.cmfz_cjw.service.AlbumService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    //查询
    @RequestMapping("/getSelect")
    public Map getSelect(int page, int rows){
        System.out.println("进来了"+page+" "+rows);
        return albumService.getAll(page,rows);
    }

    //查看详情
    @RequestMapping("/selectParticulars")
    public Map selectParticulars(int id){
        Map map = new HashMap();
        map.put("data",albumService.particulars(id));
        return map;
    }

    //添加专辑
    @RequestMapping("addAlbum")
    public Boolean addAlbum(MultipartFile file, Album album, HttpServletRequest request){
        System.out.println("添加--------");
        return albumService.addAlbum(file,album,request);
    }

    //下载
    @RequestMapping("/xiazai")
    public void getXiazai(String url, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(url);
        //获取路径
        String realPath = request.getSession().getServletContext().getRealPath("/yiyu");
        //
        File file = new File(realPath+"/"+url);

        //设置响应信息给客户端以附件的方式下载
        response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode(url.substring(url.indexOf(".")+2),"utf-8"));
        //把file以输出流的方法响应给客户端
        FileUtils.copyFile(file,response.getOutputStream());

        //获取磁盘文件
       /* String name = request.getRealPath("/yiyu");
        File file = new File(name+"/"+url);

        //设置响应信息给客户端以附件的方式下载
        response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode(url.substring(url.indexOf(".")+2),"utf-8"));
        //把file以输出流的方法响应给客户端
        FileUtils.copyFile(file,response.getOutputStream());*/
    }
}
