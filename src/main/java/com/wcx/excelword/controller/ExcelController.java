package com.wcx.excelword.controller;

import com.wcx.excelword.entity.ResultData;
import com.wcx.excelword.entity.User;
import com.wcx.excelword.service.ExcelService;
import com.wcx.excelword.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class ExcelController {
    @Resource
    private UserService userService;
    @Resource
    private ExcelService excelService;

    @RequestMapping("/excelList")
    public ResultData myExcelList(@RequestBody User user){
        String username = user.getUsername();
        User user1 = userService.findByUsername(username);
        List excelList = excelService.findByUserId(user1.getId());
        return ResultData.createSuccessJsonResult(excelList);
    }

    @RequestMapping("/excelWord")
    public ResultData excelWord(String path,String fileName){
        //TODO 首先在前端分别得到文件的路径和文件名
        // ->之后调用ExcelService中的ExcelWord方法对文件进储存
        // ->将文件转为text或word(需要上网查看实现方法)
        return null;
    }
}
