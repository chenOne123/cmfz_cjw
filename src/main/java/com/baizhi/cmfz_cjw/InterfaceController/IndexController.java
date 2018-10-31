package com.baizhi.cmfz_cjw.InterfaceController;

import com.aliyuncs.exceptions.ClientException;
import com.baizhi.cmfz_cjw.entity.Album;
import com.baizhi.cmfz_cjw.entity.User;
import com.baizhi.cmfz_cjw.service.ShouyeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class IndexController {
    @Autowired
    private ShouyeService shouyeService;

    //首页
    @RequestMapping("/first_page")
    public Map getFirst(Integer uid ,String type, String sub_type){
        System.out.println("进来了"+uid+type+sub_type);
        return shouyeService.getSelect(uid,type,sub_type);
    }

    //根据专辑id查询详细
    @RequestMapping("/wen")
    public Album getWen(Integer uid, Integer id){
        System.out.println("进来了");
        return shouyeService.getZhuanji(uid,id);
    }

    //登陆
    @RequestMapping("/login")
    public Object getLogin(String province, String password){
        return shouyeService.getLogin(province,password);
    }

    //注册
    @RequestMapping("/register")
    public Object getRegister(String phone, String passwrod){
        return shouyeService.getRegister(phone,passwrod);
    }

    //修改
    @RequestMapping("/update")
    public Object getUpdate(User user){
        System.out.println(user);
        return shouyeService.getUpdate(user);
    }

    //发送信息
    @RequestMapping("/xinxi")
    public void getXixin(String phone){
        try {
            shouyeService.fsxixin(phone);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
