package com.fh.user.mapper;


import com.fh.user.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User queryaccount(String uaccount);

    User queryphone(String uphone);

    void reguser(User user);
}
