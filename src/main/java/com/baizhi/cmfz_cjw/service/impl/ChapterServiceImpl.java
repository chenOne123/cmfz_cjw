package com.baizhi.cmfz_cjw.service.impl;

import com.baizhi.cmfz_cjw.dao.ChapterDao;
import com.baizhi.cmfz_cjw.entity.Chapter;
import com.baizhi.cmfz_cjw.service.ChapterService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.PipedReader;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;

    @Override
    public Boolean getInsert(MultipartFile file, Chapter chapter, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/");
        File file1 = new File(path+"/"+"yiyu");
        if(!file1.exists()){
            file1.mkdir();
        }
        String name = file.getOriginalFilename();
        String s = UUID.randomUUID().toString();
        String extension = FilenameUtils.getExtension(name);
        String newName = s+"."+extension;

        String s1 = UUID.randomUUID().toString();
        chapter.setId(s1);
        chapter.setUrl(newName);
        long size = file.getSize();
        chapter.setSize((int)size);
        chapter.setDuration("120Mb");
        try {
            file.transferTo(new File(file1,newName));
//            MP3File f = (MP3File) AudioFileIO.read(file1);
//            MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
//            audioHeader.getTrackLength();\
            chapterDao.insert(chapter);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
