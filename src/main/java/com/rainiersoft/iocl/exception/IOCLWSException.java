package com.rainiersoft.iocl.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User defined exception class for all the modules.
 * @author RahulKumarPamidi
 */

public class IOCLWSException extends Exception
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLWSException.class);
	private static final long serialVersionUID = -52944426521413306L;
	private int errorCode;
	private String errorMessage;

	public IOCLWSException(int errorCode, String errorMessage) 
	{
		LOG.info("ErrorCode....."+errorCode);
		LOG.info("ErrorMsg......"+errorMessage);
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
