package com.mercadolibre.w4g9projetofinal.advice;

import com.mercadolibre.w4g9projetofinal.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/***
 * Classe ResourceHandler que manipula as exceções
 *
 * @author Marcos Sá
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(
				System.currentTimeMillis(),
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro de Validação: ",
				request.getRequestURI(),
				e.getMessage());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

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

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity status code de erro e mensagem.
	 */
	@ExceptionHandler(SectionManagementException.class)
	public ResponseEntity<StandardError> sectionManagementException(SectionManagementException ex, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity status code de erro e mensagem.
	 */
	@ExceptionHandler(ExistingUserException.class)
	public ResponseEntity<StandardError> ExistingUserException(ExistingUserException ex, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), "Usúario já existente na base de dados", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity status code de erro e mensagem.
	 */
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorizationException(AuthorizationException ex, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Not Authorized", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
}
