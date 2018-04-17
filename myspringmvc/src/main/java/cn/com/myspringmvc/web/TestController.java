package cn.com.myspringmvc.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author:   shenjx
 * Date:     2018/4/16 17:05
 * Description:单例多线程
 */
@Controller()
//@Scope(value = "prototype")
public class TestController {

    private String name;

    @RequestMapping(value = "mytest")
    @ResponseBody
    public String mytest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        name=request.getParameter("name");

        //如果时间不等，返回的name结果未知
        // Thread.sleep(((int)Math.random()*5000)%3000);
        //全部返回最后一个
        Thread.sleep(2000);


        return "请求入参："+name;
    }


}
