package com.abdun.error;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.jboss.logging.Logger;

import java.util.MissingFormatArgumentException;

/**
 *
 * @author amrullah
 */
@RegisterForReflection
public class ErrorEntity {
	
	private Logger log = Logger.getLogger(ErrorEntity.class);
	private ErrorCode code;
	private String[] parameters;
	
	public ErrorEntity() {
		
	}

	public ErrorEntity(ErrorCode code, String... parameters) {
		this.code = code;
		this.parameters = parameters;
	}
	
	public ErrorCode getCode() {
		return code;
	}

	public void setCode(ErrorCode code) {
		this.code = code;
	}

	public String[] getParameters() {
		return parameters;
	}

	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}
	
	public String getMessage() {
		try {
			return String.format(code.getMessage(), (Object[]) parameters);
		} catch (MissingFormatArgumentException ex) {
			log.warn(ex.getMessage() + ". " + code.getMessage());
			return code.getMessage();
		}
	}

	@Override
	public String toString() {
		return "ErrorEntity{" + "code=" + code.name() + ", message=" + getMessage() + '}';
	}
	
}
