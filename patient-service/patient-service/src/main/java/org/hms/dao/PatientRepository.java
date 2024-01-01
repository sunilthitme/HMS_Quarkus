package org.hms.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.hms.entity.Patient;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {
}
