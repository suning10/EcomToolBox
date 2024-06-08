package com.ecom.handler;

import com.ecom.common.exception.BaseException;
import com.ecom.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler
    public Result exceptionHanlder(BaseException ex){
        log.error("error", ex.getMessage());
        return Result.error(ex.getMessage());
    }
}
