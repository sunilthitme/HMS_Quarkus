package org.hms.proxy;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.hms.entity.Patient;

import java.util.List;

@RegisterRestClient(baseUri = "http://localhost:8081/PatientService")
public interface PatientProxy {

    @GET
    @Path("/getAllPatient")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getAllPatient();

    @GET
    @Path("/getPatient/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatient(@PathParam("id") Long id);

    @POST
    @Path("/addPatient")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public String addPatient(@RequestBody Patient patient);

    @PUT
    @Path("/editPatient{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public String editPatient(@PathParam("id") Long id, Patient patient);

    @DELETE
    @Path("/deletePatient{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public String deletePatient(@PathParam("id") Long id);

    @DELETE
    @Path("/deleteAllDoctor")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String deleteAll();
}
