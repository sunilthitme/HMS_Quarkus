package org.hms.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.hms.dao.PatientRepository;
import org.hms.entity.Patient;

@Path("/PatientService")
public class PatientResource {

    @Inject
    PatientRepository  patientRepository;

    @GET
    @Path("/getAllPatient")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPatient(){
        return Response.ok(patientRepository.listAll()).build();
    }
    @GET
    @Path("/getPatient/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatient(@PathParam("id") Long id){
        Patient  patient1= patientRepository.findById(id);
        if(patient1!=null){
            return Response.ok(patient1).build();
        }

        return Response.ok(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/addPatient")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addPatient(@RequestBody Patient patient){
        patientRepository.persist(patient);
        if(patientRepository.isPersistent(patient)){
            return Response.ok("add successfully").build();
        }else {
            return Response.ok(Response.Status.BAD_REQUEST).build();
        }
    }
    @PUT
    @Path("/editPatient{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response editPatient(@PathParam("id") Long id, Patient patient){
        Patient patient1=patientRepository.findById(id);
        patient1.setName(patient.getName());
        patient1.setMobNo(patient.getMobNo());
        patient1.setIllness(patient.getIllness());
        patient1.setEmail(patient.getEmail());

        return Response.ok("set successfully").build();
    }
    @DELETE
    @Path("/deletePatient{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deletePatient(@PathParam("id") Long id){
        patientRepository.deleteById(id);

        return Response.ok("deleted successfully").build();
    }
    @DELETE
    @Path("/deleteAllDoctor")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response deleteAll(){
        patientRepository.deleteAll();
        return Response.ok("Deleted All Patient").build();
    }
}
