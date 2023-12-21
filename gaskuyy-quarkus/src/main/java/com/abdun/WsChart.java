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
@Path("chart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WsChart {

	@Inject
	private SrvChart srvChart;

	// @Path("products")
	@GET
	public List<RcdChart> getAllProductsFromChart() {

		List<RcdChart> lst = srvChart.getProductsFromChart();
		for (RcdChart rcdChart : lst) {
			srvChart.detach(rcdChart);
			rcdChart.getProductId().setChartCollection(null);
			if (rcdChart.getQty() == 0) {
				srvChart.delete(rcdChart.getId());
			}
		}
		return lst;
	}

	@Path("/{id}")
	@GET
	public RcdChart getDetailChart(@PathParam("id") int id) {
		RcdChart prd = srvChart.findById(id);
		prd.getProductId().setChartCollection(null);
		return prd;
	}

	@PUT
	public void update(RcdChart rcdChart) {
		srvChart.update(rcdChart);
	}

	@Path("/{id}")
	@DELETE
	public void deleteChart(@PathParam("id") int id) {
		srvChart.delete(id);
	}

}
