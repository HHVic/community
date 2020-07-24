package cn.huan.community.community.dto;

import cn.huan.community.community.exception.CustomizeException;
import cn.huan.community.community.exception.ICustomizeErrorCode;
import lombok.Data;

/**
 * 异常通过通用异常处理
 */
@Data
public class ResultDTO {

    private int code;
    private String message;

    public static ResultDTO SUCCESS = ok();


    public static ResultDTO error(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMessage(message);
        resultDTO.setCode(code);
        return resultDTO;
    }

    public static ResultDTO error(ICustomizeErrorCode customizeErrorCode){
        return error(customizeErrorCode.getCode(),customizeErrorCode.getMessage());
    }

    public static ResultDTO error(CustomizeException e){
        return error(e.getCode(),e.getMessage());
    }

    private static ResultDTO ok(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMessage("请求成功");
        resultDTO.setCode(200);
        return resultDTO;
    }
}
