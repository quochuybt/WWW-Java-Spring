package com.se.demorestdb.resource;

import com.se.demorestdb.dto.Cau1;
import com.se.demorestdb.dto.Cau2;
import com.se.demorestdb.model.TinTuc;
import com.se.demorestdb.service.TinTucService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/tintuc")
@Produces(MediaType.APPLICATION_JSON)
public class TinTucResource {

    @Inject
    private TinTucService tinTucService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTT () {
        List<TinTuc> tinTucs = tinTucService.getAllTinTuc();
        return Response.ok(tinTucs).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getTTById (@PathParam("id") int id) {
        TinTuc tinTuc = tinTucService.getTTById(id);
        return Response.ok(tinTuc).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTT (TinTuc tinTuc) {
        TinTuc newtinTuc = tinTucService.addTT(tinTuc);
        return Response.ok(Response.Status.CREATED).entity(newtinTuc).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateTT (@PathParam("id") int id, TinTuc tinTuc) {
        TinTuc newtinTuc = tinTucService.updateTT(id,tinTuc);
        return Response.ok(newtinTuc).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteTT (@PathParam("id") int id) {
        tinTucService.deleteTT(id);
        return Response.ok("Đã xóa tin tức thành công").build();
    }

    @GET
    @Path("/getTTWithTenDM")
    public Response getTTWithTenDM() {
        List<Cau1> cau1List = tinTucService.getTinTucWithTenDanhMuc();
        return Response.ok(cau1List).build();
    }

    @GET
    @Path("/getTTByTenDM")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTTByTenDM(@QueryParam("tenDM")String tenDM) {
        List<Cau2> cau2List = tinTucService.getTTByTenDM(tenDM);
        return Response.ok(cau2List).build();
    }
}
