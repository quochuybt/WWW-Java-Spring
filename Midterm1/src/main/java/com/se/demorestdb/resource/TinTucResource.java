package com.se.demorestdb.resource;

import com.se.demorestdb.dto.Cau1;
import com.se.demorestdb.service.TinTucService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/tintuc")
@Produces(MediaType.APPLICATION_JSON)
public class TinTucResource {

    @Inject
    private TinTucService tinTucService;

    @GET
    @Path("/getTTWithTenDM")
    public Response getTTWithTenDM() {
        List<Cau1> cau1List = tinTucService.getTinTucWithTenDanhMuc();
        return Response.ok(cau1List).build();
    }
}
