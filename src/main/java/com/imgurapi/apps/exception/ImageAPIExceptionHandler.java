package com.imgurapi.apps.exception;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ImageAPIExceptionHandler extends ResponseEntityExceptionHandler {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ImageAPIExceptionHandler.class);

	@ExceptionHandler(value = IngurAPIRestTemplateException.class)
	ResponseEntity<ErrorResponse> handleMyRestTemplateException(IngurAPIRestTemplateException ex,
			HttpServletRequest request) {
		LOGGER.error("An error happened while calling {} ExternalAPIstream API: {}", ex.getApi(), ex.toString());
		return new ResponseEntity<>(new ErrorResponse(ex, request.getRequestURI()), ex.getStatusCode());
	}
}
