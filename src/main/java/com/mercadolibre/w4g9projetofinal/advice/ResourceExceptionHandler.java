package com.mercadolibre.w4g9projetofinal.advice;

<<<<<<< HEAD
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFound;
=======
import com.mercadolibre.w4g9projetofinal.exceptions.*;
>>>>>>> cc4bc4c2dc8c4c5a0380c4a1aae0139151cd7efc
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

<<<<<<< HEAD
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFound e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
=======
/***
 * Classe ResourceHandler que manipula as exceções
 *
 * @author Marcos Sá
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity com status code de erro e mensagem.
	 */
	@ExceptionHandler(CartManagementException.class)
	public ResponseEntity<StandardError> cartManagementException(CartManagementException ex, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Não encontrado", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity status code de erro e mensagem.
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> notFoundException(ObjectNotFoundException ex, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
>>>>>>> cc4bc4c2dc8c4c5a0380c4a1aae0139151cd7efc
}
