package com.baizhi.cmfz_cjw.controller;

import com.baizhi.cmfz_cjw.entity.Chapter;
import com.baizhi.cmfz_cjw.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/getInsert")
    public Boolean getInsert(MultipartFile file, Chapter chapter, HttpServletRequest request){
        System.out.println("12344"+chapter);

        return chapterService.getInsert(file,chapter,request);
    }

}
