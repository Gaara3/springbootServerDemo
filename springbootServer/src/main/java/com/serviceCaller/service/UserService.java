package com.serviceCaller.service;


import com.serviceCaller.dao.IUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    IUserDao userdao;

    public String getPassword(String userName){
       return userdao.getPassword(userName);
    }

}
