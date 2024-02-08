package com.abdun.ws;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpStatus;
import org.hibernate.exception.ConstraintViolationException;

import com.abdun.dto.CurrentUser;
import com.abdun.rcd.RcdCart;
import com.abdun.srv.SrvCart;

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
@Path("{unique_key}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WsCart {

	@Inject
	private SrvCart srvCart;

	@Inject
	private CurrentUser currentUser;

	@GET
	public List<RcdCart> getAllProductsFromCart()  {
		List<RcdCart> lst = srvCart.getProductsFromCart(currentUser.getUserId());
		if (lst.isEmpty()) {
			throw new WebApplicationException("Produk di keranjang kosong",Response.Status.NO_CONTENT); 
		}
		for (RcdCart rcdCart : lst) {
			srvCart.detach(rcdCart);
			rcdCart.getProduct().setCartCollection(null);
			rcdCart.getProduct().setUserId(null);
			rcdCart.setUserId(null);
			if (rcdCart.getQty() == 0) {
				srvCart.delete(rcdCart.getId(), currentUser.getUserId());
			}
		}
		return lst;
	}

	@Path("/{id}")
	@GET
	public RcdCart getDetailCart(@PathParam("id") int id)  {
		RcdCart cart = srvCart.findById(id, currentUser.getUserId());
		if (cart != null) {
			cart.getProduct().setCartCollection(null);
			cart.getProduct().setUserId(null);
			cart.setUserId(null);

		} else {
			throw new WebApplicationException("Produk tidak ditemukan di keranjang",Response.Status.NOT_FOUND); 
		}
		return cart;
	}

	@POST
	public void update(RcdCart rcdCart)  {
		if (rcdCart.getId() != 0) {
			RcdCart cart = srvCart.findByProductId(rcdCart.getProductId(), currentUser.getUserId());
			if (cart != null) {
				cart.setQty(rcdCart.getQty());
				srvCart.update(cart);
			}
		} else {
			RcdCart cart = srvCart.findByProductId(rcdCart.getProductId(), currentUser.getUserId());
			if (cart != null) {
				cart.setQty(cart.getQty() + rcdCart.getQty());
				srvCart.update(cart);		
				Log.info("produk not null");
			} else {
				rcdCart.setUserId(currentUser.getUserId());
				srvCart.update(rcdCart);		
				Log.info("add new produk");
			}
		}
	}
	
	@GET
	@Path("plus/{id}")
	public void plus(@PathParam("id") int id)  {
		try {
			RcdCart cart = srvCart.findById(id, currentUser.getUserId());
			if (cart != null) {
				cart.setQty(cart.getQty() + 1);
				srvCart.update(cart);		
			} else {
				throw new WebApplicationException("Tidak ditemukan data cart", Response.Status.BAD_GATEWAY);
			}
		} catch (Exception e) {
			throw new WebApplicationException("Gagal menambah quantity produk", Response.Status.BAD_GATEWAY);
		}
	}
	
	@GET
	@Path("minus/{id}")
	public void minus(@PathParam("id") int id)  {
		try {
			RcdCart cart = srvCart.findById(id, currentUser.getUserId());
			if (cart != null) {
				cart.setQty(cart.getQty() - 1);
				srvCart.update(cart);		
			} else {
				Log.info("produk null");
			}
		} catch (Exception e) {
			throw new WebApplicationException("Gagal mengurangi quantity produk", Response.Status.BAD_GATEWAY);
		}
	}

	@Path("/{id}")
	@DELETE
	public void deleteCart(@PathParam("id") int id)  {
		srvCart.delete(id, currentUser.getUserId());			
			
	}

}
