package cn.com.myspringmvc.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author:   shenjx
 * Date:     2018/4/16 17:07
 * Description:
 */
public class MyMvcExceptionHandler implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //不是ajax请求
        if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                .getHeader("X-Requested-With") != null && request.getHeader(
                "X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            return new ModelAndView("/core/vebug/error/errorturning").addObject("error",
                    "统一错误");
        } else {
            ModelAndView mv = new ModelAndView();
            /* 使用response返回 */
            response.setStatus(HttpStatus.OK.value()); // 设置状态码
            response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
            response.setCharacterEncoding("UTF-8"); // 避免乱码
            response.setHeader("Cache-Control", "no-cache");
            try {
                response.getWriter().write("统一JSON错误");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mv;
        }

    }
}
