package org.hms.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.hms.entity.Doctor;
import org.hms.proxy.DoctorProxy;

@Path("/doctorService")
public class DoctorResource {
    @RestClient
    DoctorProxy doctorProxy;

    @GET
    @Path("/getInfo")
    public String getInfo(){
        return doctorProxy.getInfo();
    }
    @GET
    @Path("/getAllDoctor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctor(){
        return Response.ok(doctorProxy.getAllDoctor()).build();
    }
    @GET
    @Path("/getDoctor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("id") Long id){
        return Response.ok(doctorProxy.getDoctor(id)).build();
    }
    @POST
    @Path("/addoctor")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addDoctor(@RequestBody Doctor doctor){
        return Response.ok(doctorProxy.addDoctor(doctor)).build();
    }

    @PUT
    @Path("/editDoctor{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response editDoctor(@PathParam("id") Long id, Doctor doctor){
        return Response.ok(doctorProxy.editDoctor(id, doctor)).build();
    }

    @DELETE
    @Path("/deleteDoctor{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteDoctor(@PathParam("id") Long id){
        return Response.ok(doctorProxy.deleteDoctor(id)).build();
    }

    @DELETE
    @Path("/deleteAllDoctor")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response deleteAll(){
        return Response.ok(doctorProxy.deleteAll()).build();
    }
}
