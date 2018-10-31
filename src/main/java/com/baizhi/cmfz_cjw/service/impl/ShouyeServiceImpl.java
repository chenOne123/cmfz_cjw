package com.baizhi.cmfz_cjw.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baizhi.cmfz_cjw.dao.AlbumDao;
import com.baizhi.cmfz_cjw.dao.BannerDao;
import com.baizhi.cmfz_cjw.dao.GuruDao;
import com.baizhi.cmfz_cjw.dao.UserDao;
import com.baizhi.cmfz_cjw.entity.Album;
import com.baizhi.cmfz_cjw.entity.Error;
import com.baizhi.cmfz_cjw.entity.User;
import com.baizhi.cmfz_cjw.service.ShouyeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShouyeServiceImpl implements ShouyeService {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private GuruDao guruDao;
    @Autowired
    private UserDao userDao;

    //首页
    @Override
    public Map getSelect(Integer uid, String type, String sub_type) {
        System.out.println("111--"+uid+type+sub_type);
        Map map = new HashMap();
        if(uid != null && "all".equals(type)){
            System.out.println("进来了111");
            map.put("header",bannerDao.getSelect());
            map.put("album",albumDao.getAselect());
            map.put("artical",guruDao.getId(uid));
        }else if(uid != null && "wen".equals(type)){
            map.put("album",albumDao.getAselect());
        }else if(uid != null && "si".equals(type) && "ssyj".equals(sub_type)){
            map.put("artical",guruDao.getId(uid));
        }else if(uid != null && "si".equals(type) && "xmfy".equals(sub_type)){
            map.put("artical",guruDao.getXmfy(uid));
        }
        return map;
    }

    //根据专辑id查询详细
    @Override
    public Album getZhuanji(Integer uid, Integer id) {
        Album xqingqi = null;
        if(uid != null && id != null){
            xqingqi = albumDao.getXqingqi(id);
        }
        return xqingqi;
    }

    //根据电话和密码查询出用户信息
    @Override
    public Object getLogin(String province, String password) {
        User one = userDao.getOne(province, password);
        if (one == null){
            Error error = new Error();
            error.setError("-200");
            error.setTishi("密码错误");
            return error;
        }
        return one;
    }

    @Override
    public Object getRegister(String phone, String passwrod) {
        User one = userDao.getOne(phone, passwrod);
        if(one == null){
            User user = new User();
            user.setPwd(passwrod);
            user.setPhone(phone);
            return user;
        }else {
            Error error = new Error();
            error.setError("-200");
            error.setTishi("用户已经存在");
            return error;
        }
    }

    @Override
    public Object getUpdate(User user) {
        User getphone = null;
        User user1 = null;
        if(user.getProvince() != null){
            getphone = userDao.getphone(user.getPhone());
        }
        if(getphone != null){
            Error error = new Error();
            error.setError("-200");
            error.setTishi("该手机号已经存在");
            return error;
        }else{
            userDao.update(user);
            user1 = userDao.getId(user.getId());
        }
        return user1;
    }

    @Override
    public void fsxixin(String phone) throws ClientException {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAIFBm4MggRQCE6";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "QvZuybTABR8Xmrkt2QH3cmex5Nqs2Q";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("何腾飞");
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_141606919");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"8888\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
        }
    }
}
