package com.abdun;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author abdun
 */
@Path("products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WsProducts {

	@Inject
	private SrvProducts srvProducts;

	// @Path("products")
	@GET
	public List<RcdProducts> getAllProducts(@QueryParam("page") int page, @QueryParam("limit") int limit) {
		int start = (page - 1) * limit;
		int end = start + limit;
		List<RcdProducts> lst = srvProducts.getAllProducts(start, end);
		for (RcdProducts rcdProducts : lst) {
			rcdProducts.setCartCollection(null);
		}
		return lst;
	}

	// @Path("products")
	@PUT
	public void update(RcdProducts rcdProducts) {
		srvProducts.update(rcdProducts);
	}

	@Path("/{id}")
	@GET
	public RcdProducts getProductById(@PathParam("id") int id) {
		RcdProducts prd = srvProducts.findById(id);
		prd.setCartCollection(null);
		return prd;
	}

	@Path("/cat/{category}")
	@GET
	public List<RcdProducts> getProductByCategory(@PathParam("category") String category) {
		List<RcdProducts> prd = srvProducts.findByCategory(category);
		for (RcdProducts rcdProducts : prd) {
			rcdProducts.setCartCollection(null);
		}
		return prd;
	}

	@Path("/{id}")
	@DELETE
	public void deleteProduct(@PathParam("id") int id) {
		srvProducts.delete(id);
	}

}
