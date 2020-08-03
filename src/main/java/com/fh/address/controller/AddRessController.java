package com.fh.address.controller;


import com.fh.address.service.AddRessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("address")
public class AddRessController {

    @Autowired
    private AddRessService addressservice;


    @RequestMapping("queryaddresslist")
    public Map queryaddresslist(HttpServletRequest request){
        return addressservice.queryaddresslist(request);
    }
}
