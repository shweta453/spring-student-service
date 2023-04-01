package com.example.springstudentservice.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;
	private int errorCode;
	private String[] msgArgs;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String[] getMsgArgs() {
		return msgArgs;
	}

	public CustomException(int erorrCode, String message) {
		super(message);
		this.errorCode = erorrCode;
	}

	public CustomException(int errorCode, String message, HttpStatus httpStatus) {
		super(message);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	public CustomException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public CustomException(int errorCode, HttpStatus httpStatus, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	public CustomException(int errorCode, HttpStatus httpStatus, String message, String[] msgArgs) {
		super(message);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
		if (msgArgs.length > 0) {
			this.msgArgs = msgArgs;
		}
	}

	public CustomException(int errorCode, HttpStatus httpStatus, String message, String[] msgArgs,
						   Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
		if (msgArgs.length > 0) {
			this.msgArgs = msgArgs;
		}
	}

}
