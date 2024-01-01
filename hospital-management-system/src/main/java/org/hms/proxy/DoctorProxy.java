package org.hms.proxy;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;
import org.hms.entity.Doctor;

@RegisterRestClient(baseUri = "http://localhost:8181")
public interface DoctorProxy {
    @GET
    @Path("/getInfo")
    public String getInfo();

    @GET
    @Path("/getAllDoctor")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doctor> getAllDoctor();


    @GET
    @Path("/getDoctor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Doctor getDoctor(@PathParam("id") Long id);

    @POST
    @Path("/addoctor")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public String addDoctor(@RequestBody Doctor doctor);

    @PUT
    @Path("/editDoctor{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public String editDoctor(@PathParam("id") Long id, Doctor doctor);

    @DELETE
    @Path("/deleteDoctor{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public String deleteDoctor(@PathParam("id") Long id);

    @DELETE
    @Path("/deleteAllDoctor")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String deleteAll();
}
