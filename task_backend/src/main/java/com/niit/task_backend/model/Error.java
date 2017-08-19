package com.niit.task_backend.model;

public class Error {
	private int errorcode;
	private String errormessage;
	public Error(int errorcode, String errormessage) {
		super();
		this.errorcode = errorcode;
		this.errormessage = errormessage;
	}
	public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}


	
}
