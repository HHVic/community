package cn.huan.community.community.controller.advice;

import cn.huan.community.community.dto.ResultDTO;
import cn.huan.community.community.exception.CustomizeErrorCode;
import cn.huan.community.community.exception.CustomizeException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Exception e, Model model, HttpServletRequest request, HttpServletResponse response){
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            //返回json
            ResultDTO resultDTO ;
            if(e instanceof CustomizeException){
                log.error(e.getMessage());
                resultDTO =  ResultDTO.error((CustomizeException) e);
            }
            else{
                e.printStackTrace();
                log.error(e.getMessage());
                resultDTO =  ResultDTO.error(CustomizeErrorCode.SYSTEM_ERROR);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
            } catch (IOException ex) {
                log.error(e.getMessage());
                ex.printStackTrace();
            }finally {
                if(writer != null){
                    writer.close();
                }
            }
            return null;
        }else{
            if(e instanceof CustomizeException){
                model.addAttribute("message",e.getMessage());
            }
            else{
                model.addAttribute("message","服务器异常");
            }
            return new ModelAndView("error");
        }

    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
