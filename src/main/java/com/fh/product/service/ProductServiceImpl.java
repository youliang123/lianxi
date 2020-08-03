package com.fh.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.product.mapper.ProductMapper;
import com.fh.product.model.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{

    @Resource
    private ProductMapper promapper;


    @Override
    public Map queryProduct() {
        Map mm=new HashMap();

        List<String> iscakeimage=new ArrayList<>();
        List<String> image=new ArrayList<>();
        List<Product>  plist = promapper.queryProduct();
            //把热销的取出来
            for (int i = 0; i < plist.size(); i++) {
                image.add(plist.get(i).getFilepath());
                if(plist.get(i).getIscake()==1){
                    //把热销商品的图片去出
                    iscakeimage.add(plist.get(i).getFilepath());
                }
            }

        mm.put("code",200);
        mm.put("plist",plist);
        mm.put("iscakeimage",iscakeimage);
        mm.put("image",image);
        return mm;
    }

    @Override
    public Map queryProductPage(long currentPage,long pageSize) {
        Map mm=new HashMap();
        Long start = (currentPage-1)*pageSize;

       Long totlalCount = promapper.queryCount();
        List<Product> list=promapper.queryList(start,pageSize);
        Long totalPages = totlalCount%pageSize==0?totlalCount/pageSize:totlalCount/pageSize+1;
        mm.put("data",list);
        mm.put("totalPages",totalPages);
        mm.put("code",200);
        return mm;
    }

    @Override
    public Product querybyid(Integer sid) {
        Product pro=promapper.querybyid(sid);
        return pro;
    }

    @Override
    public Long updatestock(Integer sid, Integer scount) {
        return promapper.updatestock(sid,scount);
    }
}
