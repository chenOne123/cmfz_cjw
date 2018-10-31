package com.baizhi.cmfz_cjw.controller;

import com.baizhi.cmfz_cjw.entity.ChenDto;
import com.baizhi.cmfz_cjw.entity.User;
import com.baizhi.cmfz_cjw.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/select")
    public Map getSelect(int page, int rows){
        System.out.println("进来了");
        return userService.getAll(page,rows);
    }

    @RequestMapping("/daoyu")
    public void getDaoyu(String titles ,String fileds, HttpServletResponse response) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<User> select = userService.getSelect();
        System.out.println(select);
        //标题
        String[] title = titles.split(",");
        //内容
        String[] filed = fileds.split(",");
        //创建exl文件
        Workbook workbook = new HSSFWorkbook();
        //给标题单元格添加样式
        CellStyle cellStyle = workbook.createCellStyle();
        //据中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置字体
        Font font = workbook.createFont();
        //字体加超
        font.setBold(true);
        //字体颜色
        font.setColor(Font.COLOR_RED);
        //字体类型
        font.setFontName("微软雅黑");
        cellStyle.setFont(font);
        //给时间类型设置时间格式
        CellStyle cellStyle1 = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        short dataFormatFormat = dataFormat.getFormat("yyyy年mm月dd日");
        cellStyle1.setDataFormat(dataFormatFormat);
        //创建表格
        Sheet user = workbook.createSheet("user");
        //创建标题行
        Row row = user.createRow(0);
        for (int i = 0; i < title.length; i++) {
            //创建单元格 给单元格赋值
            Cell cell = row.createCell(i);
            //给单元格设置样式
            cell.setCellStyle(cellStyle);
            cell.setCellValue(title[i]);
        }
        for (int i = 0; i < select.size(); i++) {
            //创建书局行
            Row row1 = user.createRow(i + 1);
            //创建反射
            Class<?> aClass = select.get(i).getClass();
            //创建单元格给单元格赋值
            for (int j = 0; j < filed.length; j++) {
                //获取实体类的get方法
                String getMothod = "get"+filed[j].substring(0,1).toUpperCase()+filed[j].substring(1);
                //根据反射调用实体类中的方法
                Object invoke = aClass.getDeclaredMethod(getMothod, null).invoke(select.get(i), null);
                if(invoke instanceof Date){
                    System.out.println(invoke+"时间");
                    //创建单元格
                    Cell cell = row1.createCell(j);
                    cell.setCellStyle(cellStyle1);
                    //当前单元格长度为22
                    user.setColumnWidth(j,256*22);
                    cell.setCellValue((Date)invoke);
                }else if(invoke instanceof Integer){
                    Cell cell = row1.createCell(j);
                    cell.setCellValue((Integer) invoke);
                }else {
                    Cell cell = row1.createCell(j);
                    cell.setCellValue(String.valueOf(invoke));
                }
            }

        }

        Date date = new Date();
        long time = date.getTime();
        String s = time+"文件.xls";
        //导出
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(s, "utf-8"));
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //全部导出
    @RequestMapping("/allDaochu")
    public void allDaochu(HttpServletResponse response){
        List<User> select = userService.getSelect();
        List<String> titles = Arrays.asList("编号", "名字", "创建时间","省份");
        //创建exl文件
        Workbook workbook = new HSSFWorkbook();
        //给标题单元格添加样式
        CellStyle cellStyle = workbook.createCellStyle();
        //据中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置字体
        Font font = workbook.createFont();
        //字体加超
        font.setBold(true);
        //字体颜色
        font.setColor(Font.COLOR_RED);
        //字体类型
        font.setFontName("微软雅黑");
        cellStyle.setFont(font);
        //给时间类型设置时间格式
        CellStyle cellStyle1 = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        short dataFormatFormat = dataFormat.getFormat("yyyy年mm月dd日");
        cellStyle1.setDataFormat(dataFormatFormat);
        //创建表格
        Sheet user = workbook.createSheet("user");
        //创建标题行
        Row row = user.createRow(0);
        for (int i = 0; i < titles.size(); i++) {
            //创建单元格 给单元格赋值
            Cell cell = row.createCell(i);
            //给单元格设置样式
            cell.setCellStyle(cellStyle);
            cell.setCellValue(titles.get(i));
        }
        //创建内容行和内容单元格
        for (int i = 0; i < select.size(); i++) {
            Row row1 = user.createRow(i + 1);
            row1.createCell(0).setCellValue(select.get(i).getId());
            row1.createCell(1).setCellValue(select.get(i).getName());
            Cell cell = row1.createCell(2);
            cell.setCellStyle(cellStyle1);
            user.setColumnWidth(2,256*22);
            cell.setCellValue(select.get(i).getBri());
            row1.createCell(3).setCellValue(select.get(i).getProvince());
        }

        Date date = new Date();
        long time = date.getTime();
        String s = time+"文件.xls";
        //导出
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(s, "utf-8"));
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/daoru")
    public Boolean getDaoru(MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println(file);
        String realPath = request.getSession().getServletContext().getRealPath("/userExl");
        File file1 = new File(realPath);
        if(!file1.exists()){
            file1.mkdir();
        }
        String filename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(filename);
        Date date = new Date();
        String newName = date.getTime()+"."+extension;
        file.transferTo(new File(realPath,newName));
        Workbook workbook = new HSSFWorkbook(new FileInputStream(new File(file1,newName)));
        Sheet sheet = workbook.getSheet("user");
        List<User> list = new ArrayList<>();
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            Row row = sheet.getRow(i);
            double id = row.getCell(0).getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            Date date2 = row.getCell(2).getDateCellValue();
            String province = row.getCell(3).getStringCellValue();
            list.add(new User((int)id,name,date2,province));
        }

        for (User user : list) {
            userService.getInsert(user);
        }
        return true;
    }

    //统计注册
    @RequestMapping("/tjzc")
    public List getTonji(){
        return userService.tongji();
    }

    //城市统计
    @RequestMapping("/chenshi")
    public Map getChenshi(){
        return userService.chenshi();
    }
}
