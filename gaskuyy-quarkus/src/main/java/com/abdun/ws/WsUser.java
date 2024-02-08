package com.abdun.ws;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

import com.abdun.rcd.RcdUser;
import com.abdun.srv.SrvUser;

import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.SeBootstrap.Instance;
import jakarta.ws.rs.core.MediaType;
import net.bytebuddy.utility.RandomString;

/**
 *
 * @author abdun
 */
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WsUser {	

	@Inject
	SrvUser srvUser;

	@Path("new")
	@GET
	public Map<String, String> newUser(){
		String token = RandomString.make(5);
		LocalDateTime now = LocalDateTime.now().withSecond(0);
		LocalDateTime plus3Days = now.plusDays(3).withSecond(0);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		System.out.println("token = " + token);

		RcdUser rcdUser = new RcdUser();
		rcdUser.setUniqueKey(token);
		rcdUser.setCreateDate(Timestamp.valueOf(formatter.format(now)));
		rcdUser.setEndDate(Timestamp.valueOf(formatter.format(plus3Days)));
		srvUser.update(rcdUser);

		Map<String, String> result = new HashMap<>();
		result.put("unique_key", token);		
		return result;
	}

	@ActivateRequestContext
	@Scheduled(cron = "{abdun.cron}")
	void autoRemoveUser() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			List<RcdUser> users = srvUser.getUsers();
			for (RcdUser rcdUser : users) {
				if (rcdUser.getEndDate() == Timestamp.valueOf(formatter.format(now))) {
					srvUser.deleteById(rcdUser.getId());
				}
			}
		} catch (Exception e) {
			throw e;
		}

	}
	
}
