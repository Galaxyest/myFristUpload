package com.wcx.excelword.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcx.excelword.dao.ExcelDAO;
import com.wcx.excelword.dao.UserDAO;
import com.wcx.excelword.entity.User;
import com.wcx.excelword.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;
    @Resource
    private ExcelDAO excelDAO;

    @Override
    public boolean insertUser(String username, String password) {
        User user = new User();
        if (!username.isEmpty() && !password.isEmpty()) {
            user.setUsername(username);
            user.setPassword(password);
            user.setCreatetime(new Date());
            user.setUpdatetime(new Date());
            userDAO.insert(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findByUsername(String username) {
        User user = new User();
        if (!username.isEmpty()) {
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("username", username);
            user = userDAO.selectOne(queryWrapper1);
        }
        return user;
    }

    @Override
    public User findById(Integer id) {
        User user = new User();
        if (id != null) {
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("id", id);
            user = userDAO.selectOne(queryWrapper1);
        }
        return user;
    }

    @Override
    public boolean updatePwd(String username, String password) {
        User user = new User();
        if (!username.isEmpty()) {
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("username", username);
            user = userDAO.selectOne(queryWrapper1);
            user.setPassword(password);
            user.setUpdatetime(new Date());
            userDAO.insert(user);
            return true;
        } else {
            return false;
        }
    }
}
