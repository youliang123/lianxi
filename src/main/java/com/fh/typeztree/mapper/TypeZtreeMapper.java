package com.fh.typeztree.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.typeztree.model.TypeZtree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TypeZtreeMapper{
    List<Map<String,Object>> queryTypeZtree();

}
