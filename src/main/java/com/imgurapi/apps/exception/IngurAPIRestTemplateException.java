package com.imgurapi.apps.exception;

import org.springframework.http.HttpStatus;

public class IngurAPIRestTemplateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1012962164740654537L;
	private ExternalAPI api;
	  public ExternalAPI getApi() {
		return api;
	}

	public void setApi(ExternalAPI api) {
		this.api = api;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	private HttpStatus statusCode;
	  private String error;

	  public IngurAPIRestTemplateException(ExternalAPI api, HttpStatus statusCode, String error) {
	    super(error);
	    this.api = api;
	    this.statusCode = statusCode;
	    this.error = error;
	  }
}
