package com.wp.myboot.controller;

import com.wp.myboot.controller.result.SpringResult;
import com.wp.myboot.entity.UserSeNew;
import com.wp.myboot.service.UserSeNewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value="/gooController")
public class GooController {

    private final Logger log = LoggerFactory.getLogger(GooController.class);

    @Autowired
    private UserSeNewService userSeNewService;


    /**
     * 首页信息
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView getIndexJsp(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * 文件上传界面
     * @return
     */
    @RequestMapping("/upLoad")
    public ModelAndView getUploadJsp(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("upload");
        return modelAndView;
    }

}
