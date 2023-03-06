package com.wcx.excelword.service;

import com.wcx.excelword.entity.User;

public interface UserService {
    boolean insertUser(String username,String password);
    User findByUsername(String username);
    User findById(Integer id);
    boolean updatePwd(String username,String password);
}


