package com.liang3hua.springboot.web.base.common.handler;


import com.github.lianjiatech.retrofit.spring.boot.exception.RetrofitException;
import com.github.lianjiatech.retrofit.spring.boot.exception.RetrofitIOException;
import com.liang3hua.springboot.web.base.common.enums.OpCodeEnum;
import com.liang3hua.springboot.web.base.common.exception.BizException;
import com.liang3hua.springboot.web.base.common.result.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 全局异常捕捉处理
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public BaseResult errorHandler(Exception ex) {
		log.error(ex.getMessage(), ex);
		return BaseResult.fail("系统错误:"+ex.getMessage());
	}

	/**
	 * 拦截捕捉自定义异常 BizException.class
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = BizException.class)
	public BaseResult bizErrorHandler(BizException ex) {
		log.error(ex.getMessage(), ex);
		return BaseResult.fail(ex);
	}

	@ExceptionHandler(value = BindException.class)
	public BaseResult bindException(BindException ex) {
		log.error(ex.getMessage(), ex);
		return BaseResult.fail("参数校验错误:"+ex.getMessage());
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public BaseResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		log.error(ex.getMessage(), ex);
		return BaseResult.fail("参数校验错误:"+ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
	}

	@ExceptionHandler(value = RetrofitException.class)
	public BaseResult bindException(RetrofitException ex) {
		log.error(ex.getMessage(), ex);
		return BaseResult.fail(OpCodeEnum.REMOTE_CALL_ERROR);
	}

	@ExceptionHandler(value = RetrofitIOException.class)
	public BaseResult bindException(RetrofitIOException ex) {
		log.error(ex.getMessage(), ex);
		return BaseResult.fail(OpCodeEnum.REMOTE_CALL_IO_ERROR);
	}

}
