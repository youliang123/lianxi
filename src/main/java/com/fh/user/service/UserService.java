package com.fh.user.service;

import com.fh.user.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    Map queryaccount(String uaccount);

    Map queryphone(String uphone);

    Map fscode(String uphone);

    Map reguser(User user);

    Map logn(User user);

    Map joinlogin(HttpServletRequest request);

    Map out(HttpServletRequest request);
}
