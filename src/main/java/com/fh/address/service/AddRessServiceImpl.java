package com.fh.address.service;

import com.fh.address.mapper.AddRessMapper;
import com.fh.address.model.AddRess;
import com.fh.address.vo.AddRessVo;
import com.fh.common.SystemPlate;
import com.fh.user.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddRessServiceImpl implements AddRessService{

    @Resource
    private AddRessMapper addressmapper;

    @Override
    public Map queryaddresslist(HttpServletRequest request) {
        Map mm=new HashMap();

        User user = (User) request.getSession().getAttribute(SystemPlate.SESSION_KEY);
        List<AddRessVo> isup=new ArrayList<>();
        List<AddRessVo> addressList=addressmapper.queryaddresslist(user.getUid());
        if(user!=null){
            if(addressList!=null && addressList.size()>0){
                for (int i = 0; i < addressList.size(); i++) {
                    if(addressList.get(i).getIsup()==1){
                        isup.add(addressList.get(i));
                    }
                }
                mm.put("code",200);
                mm.put("data",addressList);
                mm.put("isup",isup);
                return mm;
            }
        }

        mm.put("code",201);
        return mm;
    }
}
