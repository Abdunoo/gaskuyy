package com.abdun;

import java.util.List;

import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
@Path("cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WsCart {

	@Inject
	private SrvCart srvCart;

	// @Path("products")
	@GET
	public List<RcdCart> getAllProductsFromCart() {

		List<RcdCart> lst = srvCart.getProductsFromCart();
		for (RcdCart RcdCart : lst) {
			srvCart.detach(RcdCart);
			RcdCart.getProductId().setCartCollection(null);
			if (RcdCart.getQty() == 0) {
				srvCart.delete(RcdCart.getId());
			}
		}
		return lst;
	}

	@Path("/{id}")
	@GET
	public RcdCart getDetailCart(@PathParam("id") int id) {
		RcdCart prd = srvCart.findById(id);
		prd.getProductId().setCartCollection(null);
		return prd;
	}

	@PUT
	public void update(RcdCart RcdCart) {
		srvCart.update(RcdCart);
	}

	@Path("/{id}")
	@DELETE
	public void deleteCart(@PathParam("id") int id) {
		srvCart.delete(id);
	}

}
