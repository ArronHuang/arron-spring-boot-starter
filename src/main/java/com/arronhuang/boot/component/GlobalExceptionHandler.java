package com.arronhuang.boot.component;

import com.arronhuang.boot.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ArronHuang
 * @date 2021/01/05
 */
@Component
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public R bizExceptionHandler(BizException e) {
        return R.byHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
    }

    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e) {
        log.error("捕获到未知异常", e);
        return R.byHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR, "未知错误");
    }

}
