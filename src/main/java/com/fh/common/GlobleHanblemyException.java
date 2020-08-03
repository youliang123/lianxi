package com.fh.common;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobleHanblemyException {

    @ExceptionHandler(LoginException.class)
    public Map handleLoginexception(LoginException e){
        e.printStackTrace();
        Map mm=new HashMap();
        mm.put("code",1001);
        return mm;
    }

    @ExceptionHandler(MyException.class)
    public Map handlemyexception(MyException e){
        e.printStackTrace();
        Map mm=new HashMap();
        mm.put("code",1002);
        return mm;
    }

    @ExceptionHandler(Exception.class)
    public Map handleexception(Exception e){
        e.printStackTrace();
        Map mm=new HashMap();
        mm.put("code",1003);
        return mm;
    }
}
