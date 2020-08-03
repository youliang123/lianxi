package com.fh.typeztree.service;

import com.fh.typeztree.mapper.TypeZtreeMapper;
import com.fh.typeztree.model.TypeZtree;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TypeZtrzzServiceImpl implements TypeZtreeService{

    @Resource
    private TypeZtreeMapper typemapper;

    @Override
    public List<Map<String,Object>> queryTypeZtree() {
        Map mm=new HashMap();
        List<Map<String,Object>> typezlist=typemapper.queryTypeZtree();
        List<Map<String,Object>> parentList = new ArrayList<Map<String,Object>>();
        for (Map map : typezlist) {
            if(map.get("pid").equals(0)){
                parentList.add(map);
            }
        }
        children(parentList,typezlist);
        return parentList;
    }
    public void children(List<Map<String,Object>> parentList,List<Map<String,Object>> classList){

        for (Map<String, Object> p : parentList) {
            List<Map<String,Object>> children = new ArrayList<Map<String,Object>>();
            for (Map<String, Object> a : classList) {
                if(p.get("id").equals(a.get("pid"))){
                    children.add(a);
                }
            }
            if(children!=null && children.size()>0){
                p.put("children",children);
                children(children,classList);
            }
        }
    }
}
