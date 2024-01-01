package org.hms.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.hms.dao.DoctorRepository;
import org.hms.entity.Doctor;
import org.jboss.logging.annotations.Param;

import java.util.List;
import java.util.stream.Collectors;

//http://localhost:8080/q/swagger-ui/
@Path("/")
public class DoctorResource {

    @Inject
    DoctorRepository doctorRepository;

    @GET
    @Path("getInfo")
    public String getInfo(){
        return "heelo I am from doctor";
    }

    @GET
    @Path("getAllDoctor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctor(){
        return Response.ok(doctorRepository.listAll()).build();
    }
    @GET
    @Path("/getDoctor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctor(@PathParam("id") Long id){
        Doctor doctor1=doctorRepository.findById(id);
            if(doctor1!=null){
                return Response.ok(doctor1).build();
            }

        return Response.ok(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/addoctor")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addDoctor(@RequestBody Doctor doctor){
        doctorRepository.persist(doctor);
        if(doctorRepository.isPersistent(doctor)){
            return Response.ok("add successfully").build();
        }else {
            return Response.ok(Response.Status.BAD_REQUEST).build();
        }
    }
    @PUT
    @Path("/editDoctor{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response editDoctor(@PathParam("id") Long id, Doctor doctor){
        Doctor doctor1=doctorRepository.findById(id);
                doctor1.setName(doctor.getName());
                doctor1.setSpecialization(doctor.getSpecialization());

       return Response.ok("set successfully").build();
    }
    @DELETE
    @Path("/deleteDoctor{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteDoctor(@PathParam("id") Long id){
        doctorRepository.deleteById(id);

        return Response.ok("deleted successfully").build();
    }
    @DELETE
    @Path("/deleteAllDoctor")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response deleteAll(){
        doctorRepository.deleteAll();
       return Response.ok("Deleted All Doctor").build();
    }
}
