package org.hms.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.hms.entity.Doctor;

@ApplicationScoped
public class DoctorRepository implements PanacheRepository<Doctor>{

}
