package com.example.springstudentservice.Dto;


public interface ResultCode {

	/**
	 * return http status
	 * 
	 * @return int code
	 */
	public int getHttpStatus();

	/**
	 * return result code
	 * 
	 * @return int code
	 */
	public int getResultCode();

	/**
	 * return message
	 * 
	 * @return int code
	 */
	public String getMessage();

}
