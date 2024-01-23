package com.abdun;

import java.io.IOException;
import java.util.List;

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
@Path("products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WsProducts {

	@Inject
	private SrvProducts srvProducts;

	@GET
	public List<RcdProducts> getAllProducts(@QueryParam("page") int page, @QueryParam("limit") int limit, @QueryParam("search") String searchQuery ) {
			try {
				int start = (page - 1) * limit;
				List<RcdProducts> lst = srvProducts.getAllProducts(start, limit, searchQuery);
				if (lst.isEmpty()) {
					throw new WebApplicationException("Data produk kosong",Response.Status.NO_CONTENT); 
				}
				for (RcdProducts rcdProducts : lst) {
					rcdProducts.setCartCollection(null);
				}
				return lst;

			} catch (Exception e) {
				throw new WebApplicationException("Gagal mengambil data produk",Response.Status.BAD_GATEWAY); 
			}
	}

	@POST
	public void update(@Valid RcdProducts rcdProducts) {
			try {
				srvProducts.update(rcdProducts);
			} catch (Exception e) {
				throw new WebApplicationException("Gagal update data produk",Response.Status.BAD_GATEWAY); 
			}	
	}

	@Path("/{id}")
	@GET
	public RcdProducts getProductById(@PathParam("id") int id) {
		try {
			RcdProducts prd = srvProducts.findById(id);
			if (prd != null) {
				prd.setCartCollection(null);
			}
			return prd;
		} catch (Exception e) {
			throw new WebApplicationException("Produk tidak ditemukan",Response.Status.NOT_FOUND); 
		}
	}

	@Path("/cat/{category}")
	@GET
	public List<RcdProducts> getProductByCategory(@PathParam("category") String category, @QueryParam("search") String searchQuery) {
			try {
				List<RcdProducts> prd = srvProducts.findByCategory(category, searchQuery);
			if (prd.isEmpty()) {
				throw new WebApplicationException("Data produk kosong",Response.Status.NO_CONTENT); 
			}
			for (RcdProducts rcdProducts : prd) {
				rcdProducts.setCartCollection(null);
			}
			return prd;
			} catch (Exception e) {
				throw new WebApplicationException("Gagal mengambil data produk",Response.Status.BAD_GATEWAY); 
			}
	}

	@Path("/{id}")
	@DELETE
	public void deleteProduct(@PathParam("id") int id) {
		try {
			srvProducts.delete(id);		
		} catch (Exception e) {
			throw new WebApplicationException("Gagal hapus data produk karena produk ada di keranjang",Response.Status.CONFLICT); 
		}
	}

}
