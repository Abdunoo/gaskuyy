package com.abdun.ws;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.List;

import com.abdun.dto.CurrentUser;
import com.abdun.rcd.RcdProducts;
import com.abdun.srv.SrvProducts;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author abdun
 */
@Path("{unique_key}/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WsProducts {

	@Inject
	private SrvProducts srvProducts;

	@Inject
	private CurrentUser currentUser;

	@GET
	public List<RcdProducts> getAllProducts(@QueryParam("page") int page, @QueryParam("limit") int limit, @QueryParam("category") String category, @QueryParam("search") String searchQuery ) {
		try {
			int start = (page - 1) * limit;
			List<RcdProducts> lst = srvProducts.getAllProducts(start, limit, category, searchQuery, currentUser.getUserId());
			if (lst.isEmpty() || lst == null) {
				throw new WebApplicationException("Data produk kosong",Response.Status.NO_CONTENT); 
			}
			for (RcdProducts rcdProducts : lst) {
				rcdProducts.setCartCollection(null);
				rcdProducts.setUserId(null);
			}
			return lst;

		} catch (UnknownError e) {
			throw new WebApplicationException("Gagal mengambil data produk",Response.Status.BAD_GATEWAY); 
		}

	}

	@POST
	public void update(@Valid RcdProducts rcdProducts) {
		try {
			rcdProducts.setUserId(currentUser.getUserId());
			srvProducts.update(rcdProducts);
		} catch (UnknownError e) {
			throw new WebApplicationException("Gagal update data produk",Response.Status.BAD_GATEWAY); 
		}	
	}

	@Path("/{id}")
	@GET
	public RcdProducts getProductById(@PathParam("id") int id) {
		try {
			RcdProducts prd = srvProducts.findById(id, currentUser.getUserId());
			if (prd != null) {
				prd.setCartCollection(null);
				prd.setUserId(null);
			}
			return prd;
		} catch (UnknownError e) {
			throw new WebApplicationException("Produk tidak ditemukan",Response.Status.NOT_FOUND); 
		}
	}

	// @Path("/cat/{category}/{unique_key}")
	// @GET
	// public List<RcdProducts> getProductByCategory(@PathParam("category") String category, @QueryParam("search") String searchQuery, @PathParam("unique_key") String uniqueKey) {
	// 	RcdUser user = srvUser.findByUniqueKey(uniqueKey);
	// 	if (user == null) {
	// 		throw new WebApplicationException("Unique key tidak ditemukan",Response.Status.FORBIDDEN); 
	// 	}
	// 	try {
	// 		List<RcdProducts> prd = srvProducts.findByCategory(category, searchQuery);
	// 		if (prd.isEmpty()) {
	// 			throw new WebApplicationException("Data produk kosong",Response.Status.NO_CONTENT); 
	// 		}
	// 		for (RcdProducts rcdProducts : prd) {
	// 			rcdProducts.setCartCollection(null);
	// 			rcdProducts.setUserId(null);
	// 		}
	// 		return prd;
	// 	} catch (UnknownError e) {
	// 		throw new Error("Gagal mengambil produk by category");
	// 	}
	// }

	@Path("/{id}")
	@DELETE
	public void deleteProduct(@PathParam("id") int id) {
		try {
			srvProducts.delete(id, currentUser.getUserId());		
		} catch (UnknownError e) {
			throw new WebApplicationException("Gagal hapus data produk karena produk ada di keranjang",Response.Status.CONFLICT); 
		}
	}

}
