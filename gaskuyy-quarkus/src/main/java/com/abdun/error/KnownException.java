package com.abdun.error;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author amrullah
 */
//@ApplicationException(rollback = true)
public class KnownException extends ClientErrorException {

	public KnownException(Response response) {
		super(response);
	}

	@Override
	public String toString() {
		return getResponse().getEntity().toString();
	}
	
	
	
}
