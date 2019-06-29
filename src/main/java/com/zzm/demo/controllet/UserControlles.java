package com.zzm.demo.controllet;

import com.zzm.demo.entity.User;
import com.zzm.demo.service.RedisTemplateService;
import com.zzm.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserControlles {

    @Autowired
    private UserService userService;


    @Autowired
    RedisTemplateService redisTemplateService;

//
//    @RequestMapping(value = "/select",method = RequestMethod.GET)
//    public List<User> all() {
//        List<User> allUser = userService.findAllUser();
//        return allUser;
//    }
//
//    @RequestMapping(value = "/finduser",method = RequestMethod.POST)
//    public User find(@RequestParam Integer id) {
//        User user = userService.selectUser(id);
//        return user;
//    }
//
//    @RequestMapping(value = "/update",method = RequestMethod.POST)
//    public Map update(@RequestBody User user) {
//        userService.update(user);
//        Map<String ,Object> map = new HashMap<>();
//        map.put("msg","你好");
//        map.put("data",user);
//        return map;
//    }


    @RequestMapping(value = "/select/{id}",method = RequestMethod.GET)
    public User id(@PathVariable Integer id) {
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");

        boolean b = redisTemplateService.set("list", list);
        if (b) {
            System.out.println("redis数据: "+redisTemplateService.get("list",String.class));
        }else {
            System.out.println("reids 缓存不存在");
        }

        String one = redisTemplateService.get("one", String.class);
        System.out.println("-- key : one,value : "+one);
        return userService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody User user) {
        Map<String,Object> map = new HashMap<>();
        map.put("msg","添加成功");
        map.put("data",user);
        return map;
    }
}
