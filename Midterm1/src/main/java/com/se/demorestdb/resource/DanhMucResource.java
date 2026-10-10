package com.se.demorestdb.resource;

import com.se.demorestdb.RestApplicationConfig;
import com.se.demorestdb.model.DanhMuc;
import com.se.demorestdb.service.DanhMucService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.annotations.Param;

import java.util.List;

@Path("/danhmuc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DanhMucResource {

    @Inject
    private DanhMucService danhMucService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDanhMuc() {
        List<DanhMuc> danhMucs = danhMucService.getAllDanhMuc();
        return Response.ok(danhMucs).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getDanhMucById(@PathParam("id") int id) {
        DanhMuc danhMuc = danhMucService.getDanhMucById(id);
        return Response.ok(danhMuc).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDanhMuc(DanhMuc danhMuc) {
        DanhMuc dm = danhMucService.addDanhMuc(danhMuc);
        return Response.ok(Response.Status.CREATED).entity(dm).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDanhMuc(@PathParam("id") int id,DanhMuc danhMuc) {
        DanhMuc dm = danhMucService.updateDanhMuc(id,danhMuc);
        if (dm==null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(dm).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDanhMuc(@PathParam("id") int id) {
        danhMucService.deleteDanhMuc(id);
        return Response.ok("Đã xóa thành công").build();
    }


}
