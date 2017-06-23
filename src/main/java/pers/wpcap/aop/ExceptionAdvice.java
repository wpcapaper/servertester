package pers.wpcap.aop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pers.wpcap.exception.FilterException;

/**
 * Created with IntelliJ IDEA on 2017/6/16.
 * ClassName: ExceptionAdvice
 * Created by haisong
 * Description:
 */
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(FilterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleFilter(FilterException filterException) {
        return filterException.getMessage();
    }


}
