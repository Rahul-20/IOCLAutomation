package com.rainiersoft.iocl.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOCLWSException extends Exception
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLWSException.class);
	private static final long serialVersionUID = -52944426521413306L;
	private int errorCode;
	private String errorMessage;

	public IOCLWSException(int errorCode, String errorMessage) 
	{
		LOG.info("I am hereee");
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode()
	{
		return errorCode;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}
}
