package com.demo.controller;

import com.demo.bean.User;
import com.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static Logger logger = LogManager.getLogger();

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/read={id}", method = RequestMethod.GET)
    @ResponseBody
    public User findUserById(@PathVariable("id") Integer id) {
        User user = userService.findUserById(id);
//        logger.info(user.toString());
        return user;
    }

    //http://localhost:8080/user/updata=1?&speed=1&power=2&latitude=2302.494&longitude=11321.678
    @RequestMapping(value = "/updata={id}")
    public String Updata(
            @PathVariable(value = "id") Integer id,
            @RequestParam(value = "speed") String speed,
            @RequestParam(value = "power") String power,
            @RequestParam(value = "latitude") String latitude,
            @RequestParam(value = "longitude") String longitude){

        User user = userService.findUserById(id);
        user.setSpeed(speed);
        user.setPower(power);
        user.setLatitude(latitude);
        user.setLongitude(longitude);
        userService.updateUser(user);
//        logger.info(user.toString());
        return "success";
    }

//    @RequestMapping(value = "/insert={id}")
//    public String Insert(
//            @PathVariable(value = "id") Integer id,
//            @RequestParam(value = "name") String name,
//            @RequestParam(value = "password") String password){
//        //User user = userService.findUserById(id);
//        //new一个User对象存储新增的信息
//        User user = new User();
//        user.setId(id);
//        user.setName(name);
//        user.setPassword(password);
//        userService.insertUser(user);
//        logger.info(user.toString());
//        return "success";
//    }
}
