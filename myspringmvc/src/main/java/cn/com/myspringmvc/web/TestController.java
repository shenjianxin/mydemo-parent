package cn.com.myspringmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author:   shenjx
 * Date:     2018/4/16 17:05
 * Description:
 */
@Controller
public class TestController {

    @RequestMapping(value = "mytest",method = RequestMethod.GET)
    @ResponseBody
    public String mytest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("1111111111111");
        return "请求成功";
    }


}
