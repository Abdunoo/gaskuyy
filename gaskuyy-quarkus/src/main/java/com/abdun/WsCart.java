package com.abdun;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpStatus;
import org.hibernate.exception.ConstraintViolationException;

import io.quarkus.logging.Log;
import io.quarkus.qute.ErrorCode;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author abdun
 */
@Path("cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WsCart {

	@Inject
	private SrvCart srvCart;

	// @Path("products")
	@GET
	public List<RcdCart> getAllProductsFromCart()  {
					List<RcdCart> lst = srvCart.getProductsFromCart();
				if (lst.isEmpty()) {
					throw new WebApplicationException("Produk di keranjang kosong",Response.Status.NO_CONTENT); 
				}
				for (RcdCart RcdCart : lst) {
					srvCart.detach(RcdCart);
					RcdCart.getProduct().setCartCollection(null);
					if (RcdCart.getQty() == 0) {
						srvCart.delete(RcdCart.getId());
					}
				}
				return lst;
	}

	@Path("/{id}")
	@GET
	public RcdCart getDetailCart(@PathParam("id") int id)  {
				RcdCart prd = srvCart.findById(id);
			if (prd != null) {
				prd.getProduct().setCartCollection(null);
			} else {
				throw new WebApplicationException("Produk tidak ditemukan di keranjang",Response.Status.NOT_FOUND); 
			}
			return prd;
	}

	@POST
	public void update(RcdCart rcdCart)  {
			if (rcdCart.getId() != 0) {
				RcdCart prd = srvCart.findByProductId(rcdCart.getProductId());
				if (prd != null) {
					prd.setQty(rcdCart.getQty());
					srvCart.update(prd);		
				}
			} else {
				RcdCart prd = srvCart.findByProductId(rcdCart.getProductId());
				if (prd != null) {
					prd.setQty(prd.getQty() + rcdCart.getQty());
					srvCart.update(prd);		
					Log.info("produk not null");
				} else {
					srvCart.update(rcdCart);		
					Log.info("produk null");
				}
			}
				
	}

	@Path("/{id}")
	@DELETE
	public void deleteCart(@PathParam("id") int id)  {
			srvCart.delete(id);			
			
	}

}
