/**
 * 
 */
package com.task.common.exception;

import java.text.ParseException;
import java.util.List;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author manchanda.a
 *
 */
@ControllerAdvice
public class ExceptionTranslator {

	/**
	 * Parsing exception.
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ParseException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorVM> processDatabaseError(DataAccessException  ex) {
		BodyBuilder builder;
		ErrorVM errorVM;
		builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		errorVM = new ErrorVM("Problem processing request, Please contact system administrator");
		return builder.body(errorVM);
	}
	
	/**
	 * Method Argument not valid exception handling.
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorVM processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		return processFieldErrors(fieldErrors);
	}
	
	/**
	 * Generic exception handling.
	 * @param ex
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorVM> processRuntimeException(Exception ex) throws Exception {
		BodyBuilder builder;
		ErrorVM errorVM;
		ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
		if (responseStatus != null) {
			builder = ResponseEntity.status(responseStatus.value());
			errorVM = new ErrorVM("error." + responseStatus.value().value(), responseStatus.reason());
		} else {
			builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
			errorVM = new ErrorVM("Internal server error");
		}
		return builder.body(errorVM);
	}
	
	/**
	 * This method will process field errors.
	 * @param fieldErrors
	 * @return
	 */
	private ErrorVM processFieldErrors(List<FieldError> fieldErrors) {
		ErrorVM dto = new ErrorVM("Data entered by user is not correct, Please contact system administrator");

		for (FieldError fieldError : fieldErrors) {
			dto.add(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode());
		}

		return dto;
	}
}
