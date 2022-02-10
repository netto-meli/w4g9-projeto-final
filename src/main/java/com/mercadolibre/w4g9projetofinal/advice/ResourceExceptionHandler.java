package com.mercadolibre.w4g9projetofinal.advice;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mercadolibre.w4g9projetofinal.exceptions.BusinessException;
import com.mercadolibre.w4g9projetofinal.exceptions.CartManagementException;
import com.mercadolibre.w4g9projetofinal.exceptions.ExistingUserException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.exceptions.SectionManagementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
	 * @return Response Entity status code de erro e mensagem.
	 */
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<StandardError> usernameNotFoundException(UsernameNotFoundException ex,
																   HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(),
				"Usuário não encontrado", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity com status code de erro e mensagem.
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
																		 HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro ao executar SQL, provável violação de Constraint", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity com status code de erro e mensagem.
	 */
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<StandardError> invalidFormatException(InvalidFormatException ex,
																HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Formáto Inválido", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity com status code de erro e mensagem.
	 */
	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<StandardError> jsonParseException(JsonParseException ex,
															HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Problema ao realizar Parsing do JSON", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity com status code de erro e mensagem.
	 */
	@ExceptionHandler(CartManagementException.class)
	public ResponseEntity<StandardError> cartManagementException(CartManagementException ex,
																 HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Não encontrado", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity com status code de erro e mensagem.
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException ex,
																  HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Não encontrado", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}


	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity com status code de erro e mensagem.
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<StandardError> businessException(BusinessException ex,
														   HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Não encontrado", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity status code de erro e mensagem.
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> notFoundException(ObjectNotFoundException ex,
														   HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Não encontrado", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity status code de erro e mensagem.
	 */
	@ExceptionHandler(SectionManagementException.class)
	public ResponseEntity<StandardError> sectionManagementException(SectionManagementException ex,
																	HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Não encontrado", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity status code de erro e mensagem.
	 */
	@ExceptionHandler(ExistingUserException.class)
	public ResponseEntity<StandardError> existingUserException(ExistingUserException ex,
															   HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(),
				"Usúario já existente na base de dados", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return Response Entity status code de erro e mensagem.
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<StandardError> accessDeniedException(AccessDeniedException ex,
															   HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(),
				"Not Authorized", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
}