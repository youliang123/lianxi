package com.fh.address.mapper;

import com.fh.address.model.AddRess;
import com.fh.address.vo.AddRessVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddRessMapper {

    List<AddRessVo> queryaddresslist(Long uid);
}
