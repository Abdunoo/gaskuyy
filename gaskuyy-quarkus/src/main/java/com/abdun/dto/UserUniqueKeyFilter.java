package com.abdun.dto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.abdun.rcd.RcdUser;
import com.abdun.srv.SrvUser;

import io.quarkus.logging.Log;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(10)
public class UserUniqueKeyFilter implements ContainerRequestFilter {
	@Inject
	CurrentUser currentUser;

	@Inject
	SrvUser srvUser;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getUriInfo().getPathParameters().containsKey("unique_key")) {
			RcdUser user = srvUser.findByUniqueKey(requestContext.getUriInfo().getPathParameters().getFirst("unique_key") );
			if (user == null) {
				throw new WebApplicationException("Unique key tidak ditemukan",Response.Status.FORBIDDEN); 
			}
			currentUser.setUserId(user.getId());
		}
		System.out.println("url " + requestContext.getUriInfo().getPathParameters().containsKey("unique_key"));
	}
}
