package com.baizhi.cmfz_cjw.service;

import com.baizhi.cmfz_cjw.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ChapterService {
    //添加专辑
    Boolean getInsert(MultipartFile file, Chapter chapter, HttpServletRequest request);
}
