package com.vetcloud.appointments_srv.service;

import com.vetcloud.appointments_srv.dto.AppointmentRequest;
import com.vetcloud.appointments_srv.dto.AppointmentConfirmationDto;
import com.vetcloud.appointments_srv.model.Appointment;
import com.vetcloud.appointments_srv.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AppointmentService {

  private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

  private final AppointmentRepository repository;
  private final WebClient webClient;

  @Value("${azure.functions.notify-url}")
  private String azureFunctionUrl;

  public AppointmentService(AppointmentRepository repository, WebClient webClient) {
    this.repository = repository;
    this.webClient = webClient;
  }

  public void createAppointment(AppointmentRequest request) {
    Appointment appointment = new Appointment();
    appointment.setClientName(request.getClientName());
    appointment.setPetName(request.getPetName());
    appointment.setEmail(request.getEmail());
    appointment.setReason(request.getReason());
    appointment.setAppointmentDate(request.getAppointmentDate());

    repository.save(appointment);

    // 🔁 Mapear a lo que espera la Azure Function
    AppointmentConfirmationDto confirmation = new AppointmentConfirmationDto(
        "123e4567-e89b-12d3-a456-426614174000", // patientId
        "456e7890-e89b-12d3-a456-426614174111", // vetId
        appointment.getAppointmentDate().toString(),
        appointment.getReason(),
        "SCHEDULED");

    // Notify Azure Function
    logger.info("🔄 Enviando confirmación a Azure Function en: {}", azureFunctionUrl);

    webClient.post()
        .uri(azureFunctionUrl)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .bodyValue(confirmation)
        .retrieve()
        .bodyToMono(Void.class)
        .doOnSuccess(res -> logger.info("✅ Confirmación enviada correctamente a Azure Function"))
        .doOnError(error -> logger.error("❌ Error al enviar confirmación a Azure Function", error))
        .subscribe();
  }
}
