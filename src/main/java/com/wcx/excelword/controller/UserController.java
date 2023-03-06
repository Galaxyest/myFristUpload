package com.wcx.excelword.controller;

import com.wcx.excelword.entity.ResultData;
import com.wcx.excelword.entity.User;
import com.wcx.excelword.service.ExcelService;
import com.wcx.excelword.service.UserService;
import com.wcx.excelword.utils.TokenUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private ExcelService excelService;

    @RequestMapping("/register")
    public ResultData register(@RequestBody User user){
        //判断当前是否被注册过
        String username = user.getUsername();
        User user1 = userService.findByUsername(username);
        if (user1 != null){
            userService.insertUser(user.getUsername(),user.getPassword());
            return ResultData.createSuccessJsonResult("注册成功");
        }else {
            return ResultData.createFailJsonResult("605","注册失败，用户名已被占用");
        }
    }

    @RequestMapping("/login")
    public ResultData login(@RequestBody User user,HttpSession session){
        User userK = userService.findByUsername(user.getUsername());
        if (userK.getPassword().equals(user.getPassword())){
            final String token = TokenUtil.createToken(user.getUsername());
            session.setAttribute("token",token);
            return ResultData.createSuccessJsonResult("登录成功");
        }else {
            return ResultData.createFailJsonResult("604","用户名或密码错误");
        }
    }


}
