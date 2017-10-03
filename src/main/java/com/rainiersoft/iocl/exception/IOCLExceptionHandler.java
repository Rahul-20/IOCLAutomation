package com.rainiersoft.iocl.exception;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rainiersoft.response.dto.ErrorResponseBean;


/**
 * User defined exception Handler for all the modules.
 * @author RahulKumarPamidi
 */

@Provider
@Singleton
public class IOCLExceptionHandler implements ExceptionMapper<IOCLWSException>
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLExceptionHandler.class);
	public Response toResponse(IOCLWSException exception) 
	{
		LOG.info("I am in handler:::::"+exception.getErrorCode()+"::::"+exception.getErrorMessage());
		ErrorResponseBean errorBean=new ErrorResponseBean(exception.getErrorCode(), exception.getErrorMessage());
		return Response.status(exception.getErrorCode()).entity(errorBean).build();
	}
}
