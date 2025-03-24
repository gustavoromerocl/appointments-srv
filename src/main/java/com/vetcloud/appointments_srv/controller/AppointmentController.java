package com.vetcloud.appointments_srv.controller;

import com.vetcloud.appointments_srv.dto.AppointmentRequest;
import com.vetcloud.appointments_srv.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

  private final AppointmentService service;

  public AppointmentController(AppointmentService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<String> createAppointment(@RequestBody AppointmentRequest request) {
    service.createAppointment(request);
    return ResponseEntity.ok("Appointment created and notification sent.");
  }
}
