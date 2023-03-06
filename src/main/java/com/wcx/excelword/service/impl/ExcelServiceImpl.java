package com.wcx.excelword.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcx.excelword.dao.ExcelDAO;
import com.wcx.excelword.entity.ExcelInfo;
import com.wcx.excelword.service.ExcelService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;


public class ExcelServiceImpl implements ExcelService {
    @Resource
    private ExcelDAO excelDAO;

    @Override
    public List<ExcelInfo> findByUserId(Integer id) {
        List<ExcelInfo> excelInfoList = null;
        if (id != null){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_id",id);
            excelInfoList = excelDAO.selectList(queryWrapper);
        }
        return excelInfoList;
    }

    @Override
    public String excelword(String path) {
        path = "C:\\Users\\银河湾\\Desktop\\test1.xlsx";
        File file = new File(path);
        ExcelInfo excelInfo = new ExcelInfo();

        Map map = new HashMap();
        try {
            // 1. 创建文件输入流
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            // 2. 根据该数据流 创建HSSFWorkbook对象
            XSSFWorkbook hssfWorkbook = new XSSFWorkbook(bis);
            // 3. 获得到Sheet（这里只有一个工作表）
            XSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            // 4. 判断sheet中有多少行数据
            int rowNum = sheet.getLastRowNum();

            // 5. 遍历每一行数据，获取HSSFRow对象
            for(int i = 0; i < rowNum; i++)
            {
                XSSFRow row = sheet.getRow(i);
                // 获取每一行的列数
                short lastCellNum = row.getLastCellNum();
                StringBuilder sb = new StringBuilder();
                // 6. 遍历每一行中的每一列，获取到每个单元格中的值

                List<XSSFCell> celList = new ArrayList<>();
                String r = String .valueOf(i);
                map.put(r,null);
                for(int j = 0; j < lastCellNum; j++)
                {
                    XSSFCell cell = row.getCell(j);

                    celList.add(cell);
                }
                map.put(r,celList.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String excel = map.toString();
        if (!excel.isEmpty()){
            excelInfo.setContent(excel);
            System.out.println("excelContent" + excel);
//            File file1 = new File("C:\\Users\\银河湾\\Desktop\\民办大学名单" + ".txt");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("C:\\Users\\银河湾\\Desktop\\test.txt",true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将数据写入text文档中
            BufferedWriter bw = new BufferedWriter(fileWriter);
            try {
                bw.write(excel);
                bw.close();
                BufferedOutputStream op = new BufferedOutputStream(new FileOutputStream(String.valueOf(fileWriter)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            excelInfo.setCreatetime(new Date());
            excelInfo.setUpdatetime(new Date());
            //储存excel的信息
            excelDAO.insert(excelInfo);
            return "SUCCESS";
        }else {
            return "FALSE";
        }
    }

    @Override
    public String saveExcel(String path) {
        return null;
    }
}
