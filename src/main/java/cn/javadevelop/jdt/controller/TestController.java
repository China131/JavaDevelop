package cn.javadevelop.jdt.controller;

import cn.javadevelop.jdt.mapper.read.TestReadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by jianhao on 2017/11/29.
 */
@RequestMapping(value = "/api")
@Controller
public class TestController {

    @Autowired
    private TestReadMapper testReadMapper;
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        List<Map> list = testReadMapper.selectTest();

        return list.toString();
    }

}




