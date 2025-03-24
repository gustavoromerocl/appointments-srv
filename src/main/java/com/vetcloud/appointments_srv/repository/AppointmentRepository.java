package com.vetcloud.appointments_srv.repository;

import com.vetcloud.appointments_srv.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
