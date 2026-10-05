package iuh.fit.bai5_restapi.resource;

import iuh.fit.bai5_restapi.model.Department;
import iuh.fit.bai5_restapi.service.DepartmentService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/departments")
public class DepartmentResource {

    @Inject
    private DepartmentService departmentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return Response.ok(departments).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentById(@PathParam("id") int id) {
        Department department = departmentService.getDepartmentById(id);

        if (department == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(department).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDepartment(Department department) {
        Department newDepartment = departmentService.addDepartment(department);

        return Response
                .status(Response.Status.CREATED)
                .entity(newDepartment)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDepartment(
            @PathParam("id") int id,
            Department department) {

        Department updatedDepartment = departmentService.updateDepartment(id, department);

        if (updatedDepartment == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updatedDepartment).build();
    }
}
