package com.packtpub.chapter6.rest;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.joda.time.DateTime;

import com.packtpub.chapter6.ejb.LoggingBean;

@Stateless
@Path("logging")
@Produces("application/json")
public class BasicWebService {

    @Inject
    private LoggingBean loggingBean;

    @GET
    @Path("example")
    public Object getCurrentPlayers() {

        loggingBean.log();
        
        DateTime date = new DateTime(new Date());
        
        // return Response.ok().build();
        JsonObject model = Json.createObjectBuilder()
                .add("date", date.toString("yyyy-MM-dd hh:mm:ss"))
//                .add("result", "OK")
                .build();

        return model;

    }

}
