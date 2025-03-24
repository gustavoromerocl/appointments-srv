package com.vetcloud.appointments_srv.dto;

public class AppointmentConfirmationDto {
  private String patientId;
  private String vetId;
  private String appointmentDate;
  private String reason;
  private String status;

  public AppointmentConfirmationDto() {
  }

  public AppointmentConfirmationDto(String patientId, String vetId, String appointmentDate, String reason,
      String status) {
    this.patientId = patientId;
    this.vetId = vetId;
    this.appointmentDate = appointmentDate;
    this.reason = reason;
    this.status = status;
  }

  public String getPatientId() {
    return patientId;
  }

  public String getVetId() {
    return vetId;
  }

  public String getAppointmentDate() {
    return appointmentDate;
  }

  public String getReason() {
    return reason;
  }

  public String getStatus() {
    return status;
  }

  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }

  public void setVetId(String vetId) {
    this.vetId = vetId;
  }

  public void setAppointmentDate(String appointmentDate) {
    this.appointmentDate = appointmentDate;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
