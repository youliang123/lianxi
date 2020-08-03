package com.fh.typeztree.controller;

import com.fh.typeztree.service.TypeZtreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("typez")
@RestController
public class TypeZtreeController {

    @Autowired
    private TypeZtreeService typeservice;


    @RequestMapping("querytypez")
    public Map queryTypeZtree(){
        Map mm=new HashMap();
        List<Map<String,Object>> list=typeservice.queryTypeZtree();
        mm.put("code",200);
        mm.put("data",list);
        return mm;
    }
}
