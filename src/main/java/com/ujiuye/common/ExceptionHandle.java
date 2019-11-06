package com.ujiuye.common;

import com.ujiuye.exception.ZhiFuException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = {SQLException.class})
    @ResponseBody
    public ResultEntity handleSqlException(){
        return ResultEntity.error();
    }

    @ExceptionHandler(value = {ZhiFuException.class})
    public ResultEntity handleZhiFuException(){
        return ResultEntity.error();
    }
}
