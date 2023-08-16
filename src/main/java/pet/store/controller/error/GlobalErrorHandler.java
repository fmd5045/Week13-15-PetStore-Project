package pet.store.controller.error;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, String> handleNoSuchErrorException(NoSuchElementException exception) {
		log.info("NoSuchElementException occured: {}", exception.getMessage());

		Map<String, String> errorResponse = new HashMap<String, String>();
		errorResponse.put("message", exception.toString());

		return errorResponse;
	}

	@ExceptionHandler(UnsupportedOperationException.class)
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public Map<String, String> hadleUnsupportedOPerationException(UnsupportedOperationException exception) {
		log.info("UnsupportedOperationException occured: {}", exception.getMessage());
		Map<String, String> errorResponse = new HashMap<String, String>();
		errorResponse.put("message", exception.toString());
		
		return errorResponse;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleException(Exception exception) {
		log.info("Exception occured: {}", exception.getMessage());
		Map<String, String> errorResponse = new HashMap<String, String>();
		errorResponse.put("message", exception.toString());
		return errorResponse;
	}
	
}
