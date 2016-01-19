package com.smartweights.mail.exception;

public class SmartWeightsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exceptionMessage;
	private int errorCode;
	private String applicationError;
	private String className;
	private String methodName;

	public SmartWeightsException() {
	}

	public SmartWeightsException(String exMsg) {
		this.exceptionMessage = exMsg;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public SmartWeightsException(int errCode, String exMsg, String appErr, String clName, String method) {
		this.errorCode = errCode;
		this.exceptionMessage = exMsg;
		this.applicationError = appErr;
		this.className = clName;
		this.methodName = method;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setApplicationError(String applicationError) {
		this.applicationError = applicationError;
	}

	public String getApplicationError() {
		return applicationError;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}

}
