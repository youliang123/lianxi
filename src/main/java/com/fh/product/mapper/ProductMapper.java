package com.fh.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.product.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    List<Product> queryProduct();

    @Select("select count(*) from t_pro")
    Long queryCount();

    @Select("select * from t_pro order by proid desc limit #{start},#{pageSize}")
    List<Product> queryList(@Param("start") long start,@Param("pageSize") long pageSize);

    @Select("select * from t_pro where proid=#{sid}")
    Product querybyid(Integer sid);

    Long updatestock(@Param("sid") Integer sid,@Param("scount") Integer scount);
}
