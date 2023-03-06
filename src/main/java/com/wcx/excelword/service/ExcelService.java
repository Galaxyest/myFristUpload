package com.wcx.excelword.service;

import com.wcx.excelword.entity.ExcelInfo;

import java.util.List;

public interface ExcelService {
    List<ExcelInfo> findByUserId(Integer id);
    String excelword(String path);
    String saveExcel(String path);
}
