package com.mercadolibre.w4g9projetofinal.advice;

import com.mercadolibre.w4g9projetofinal.exceptions.CartManagementException;
import com.mercadolibre.w4g9projetofinal.exceptions.ErrorProcesamentoException;
import com.mercadolibre.w4g9projetofinal.exceptions.NotFoundExceptionProduct;
import com.mercadolibre.w4g9projetofinal.exceptions.RepositoryException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/***
 * Classe Advice que manipula as exceções
 *
 * @author Fernando
 */
@ControllerAdvice
public class PersistenceExceptionAdvice {

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return ResponseEntity com status code de erro e mensagem.
	 */
	@ExceptionHandler(value = CartManagementException.class)
	protected ResponseEntity<Object> handleCartManagementException(CartManagementException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return ResponseEntity.unprocessableEntity().body(bodyOfResponse);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return ResponseEntity com status code de erro e mensagem.
	 */
	@ExceptionHandler(value = ErrorProcesamentoException.class)
	protected ResponseEntity<Object> handleErrorProcesamentoException(ErrorProcesamentoException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return ResponseEntity.unprocessableEntity().body(bodyOfResponse);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return ResponseEntity com status code de erro e mensagem.
	 */
	@ExceptionHandler(value = NotFoundExceptionProduct.class)
	protected ResponseEntity<Object> erroNotFoundExceptionProduct(NotFoundExceptionProduct ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return ResponseEntity.unprocessableEntity().body(bodyOfResponse);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return ResponseEntity com status code de erro e mensagem.
	 */
	@ExceptionHandler(value = RepositoryException.class)
	protected ResponseEntity<Object> erroRepositoryException(RepositoryException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return ResponseEntity.unprocessableEntity().body(bodyOfResponse);
	}
}
