package org.hms.resource;


import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.hms.entity.Patient;
import org.hms.proxy.PatientProxy;



@Path("/patientService")
public class PatientResource {
    @RestClient
    PatientProxy patientProxy;

    @GET
    @Path("/getAllPatient")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPatient(){
        return Response.ok(patientProxy.getAllPatient()).build();
    }

    @GET
    @Path("/getPatient/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatient(@PathParam("id") Long id){
        return Response.ok(patientProxy.getPatient(id)).build();
    }

    @POST
    @Path("/addPatient")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addPatient(@RequestBody Patient patient){
       return Response.ok(patientProxy.addPatient(patient)).build();
    }
    @PUT
    @Path("/editPatient{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response editPatient(@PathParam("id") Long id, Patient patient){
        return Response.ok(patientProxy.editPatient(id,patient)).build();

    }

    @DELETE
    @Path("/deletePatient{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deletePatient(@PathParam("id") Long id){
        return Response.ok(patientProxy.deletePatient(id)).build();
    }

    @DELETE
    @Path("/deleteAllDoctor")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response deleteAll(){
        return Response.ok(patientProxy.deleteAll()).build();
    }
}
