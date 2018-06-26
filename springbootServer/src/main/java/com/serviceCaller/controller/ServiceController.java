package com.serviceCaller.controller;

import com.serviceCaller.config.JwtInfo;
import com.serviceCaller.tools.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.serviceCaller.pojo.LoginReq;
import com.serviceCaller.pojo.LoginRes;
import com.serviceCaller.pojo.ServiceRequest;
import com.serviceCaller.pojo.ServiceResponse;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import com.serviceCaller.service.UserService;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import io.jsonwebtoken.Jwts;

@RestController
public class ServiceController {

    @Resource
    private UserService userService;

    @Value("${EXELOCATION}")
    private String serviceLocation;

    @Autowired
    private JwtInfo jwtInfo;

    private static final long EXPIRATIONTIME = 1000*60*60; // 1 hour
    private final static Logger logger = LoggerFactory.getLogger(ServiceController.class);

    /**
     * @Author: Shixiong
     * @Description
     * @param req
     * @Date: 2018/6/5
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public LoginRes login(@RequestBody LoginReq req){
        String operator = req.getOperator();
        logger.info("Login request received,user:{}",operator);
        LoginRes res = new LoginRes("failure");

        String pwdFromDB =userService.getPassword(operator);

        if(!req.getPassword().equals(pwdFromDB))//Using @NotBlank in LoginReq to avoid null pwd
            return res;  //认证失败直接返回failure
        String JWT = JwtHelper.createJWT(operator,jwtInfo.getClientId(),jwtInfo.getName(),
                        jwtInfo.getExpiresSecond() * 1000, jwtInfo.getBase64Secret());
        res.setToken(JWT);
        res.setResult("success");
        return res;
    }

    /**
     * @Author: Shixiong
     * @Description:The method to invoke executable service
     * @param req:
     * @Date: 2018/6/5
     */
    @RequestMapping(value = "/service",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public ServiceResponse serviceCaller(@RequestBody ServiceRequest req){
        String operator = req.getOperator();
        String serviceName = req.getServiceName();
        logger.info("Service request received,operator :{},serviceName :{}",operator,serviceName);
        Runtime rn =Runtime.getRuntime();
        String[] commandArgs = {serviceLocation.concat(req.getServiceName())};  //serviceLocation comes from application.yml
        ServiceResponse res = new ServiceResponse(req.getServiceName());
        try {
            //Process p =rn.exec(commandArgs);   TODO Comment for test
            //p.waitFor();  等待p运行完成
            TimeUnit.SECONDS.sleep(2);
        }/* catch (IOException e) {
            logger.error("Something wrong with the serviceCaller",e);
            res.setResponseStatus(-1);
            res.setMissionStatus(-1);
            logger.error("Service "+req.getServiceName()+"invoking failed",e);  //TODO Change into Stringbuilder
        } */catch (InterruptedException e){
            e.printStackTrace();
            res.setMissionStatus(-1);
            logger.error("Service Runtime error",e);
        }
        res.setTimestamp(String.valueOf(System.currentTimeMillis()/1000));
        logger.info("Service invoked successfully.ServiceName:{},operator:{},responseStatus:{},missionStatus:{}",serviceName,operator,res.getResponseStatus(),res.getMissionStatus());
        return res;
    }

    /**
     * @Author: Shixiong
     * @Description:A test method
     * @param username
     * @param pwd
     * @Date: 2018/6/5
     */
    @RequestMapping(value = "/users/{username}",method = RequestMethod.GET,consumes="application/json")
    public String getUser(@PathVariable String username, @RequestParam String pwd){
        logger.info("testlogger",pwd,"logger2");
        return "Welcome,"+username;
    }

    /**
     * @Author: Shixiong
     * @Description:A test method
     * @param username
     * @param pwd
     * @Date: 2018/6/5
     */
    @RequestMapping(value = "/test/{username}",method = RequestMethod.GET,consumes = "application/json")
    public String getPwd(@PathVariable String username,@RequestParam String pwd){
        String pwdFromDB =userService.getPassword(username);

        return pwd.equals(pwdFromDB)?"loginSuccess":"pwdWrong";//
    }


}
