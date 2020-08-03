package com.fh.pay;


import com.fh.sdk.MyConfig;
import com.fh.sdk.WXPay;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("pay")
public class payController {

    @RequestMapping("builorder")
    public Map createNative(String orderNo){
        Map mm=new HashMap();
        try {
        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "幼凉商品付费中心");
        data.put("out_trade_no", orderNo);
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", "12");


            Map<String, String> resp = wxpay.unifiedOrder(data);
            //判断通信是否成功
            if(!resp.get("return_code").equalsIgnoreCase("SUCCESS")){
                mm.put("code",201);
                return mm;
            }
            //判断业务结果是否正确
            if(!resp.get("result_code").equalsIgnoreCase("SUCCESS")){
                System.out.println("==============="+resp.get("err_code_des"));
                mm.put("code",201);
                return mm;
            }
            //return_code 和result_code都为SUCCESS
            String code_url = resp.get("code_url");
            mm.put("code",200);
            mm.put("data",code_url);

            //System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mm;
    }

    @RequestMapping("querypay")
    public Map querypay(String orderNo){
        Map mm=new HashMap();
        try {
            int count=0;

            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config);

            Map<String, String> data = new HashMap<String, String>();
            data.put("out_trade_no",orderNo);

            for(;;){



            Map<String, String> resp = wxpay.orderQuery(data);
            //判断通信是否成功
            if(!resp.get("return_code").equalsIgnoreCase("SUCCESS")){
                System.out.println("==============="+resp.get("return_msg"));
                mm.put("code",201);
                return mm;
            }
            //判断业务结果是否正确
            if(!resp.get("result_code").equalsIgnoreCase("SUCCESS")){
                System.out.println("==============="+resp.get("err_code_des"));
                mm.put("code",201);
                return mm;
            }
            if(resp.get("trade_state").equalsIgnoreCase("SUCCESS")){
                mm.put("code",200);
                return mm;
            }

            Thread.sleep(3000);
            count++;
            if(count > 5){
                mm.put("code",202);
                return mm;
            }
          }
            //System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
            mm.put("code",203);
            return mm;
        }


    }
}
