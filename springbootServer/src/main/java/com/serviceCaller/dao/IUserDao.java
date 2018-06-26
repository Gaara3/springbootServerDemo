package com.serviceCaller.dao;

import com.serviceCaller.pojo.UserInfo;
import org.springframework.stereotype.Repository;

//@Repository
public interface IUserDao {
    String getPassword(String userName);
}
