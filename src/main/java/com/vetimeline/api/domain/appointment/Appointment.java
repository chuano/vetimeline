package com.vetimeline.api.domain.appointment;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class Appointment {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "date")
    private Date date;

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID pet;

    @Embedded
    private AppointmentDescription description;

    public Appointment() {}

    public Appointment(UUID id, Date date, UUID pet) {
        this.id = id;
        this.date = date;
        this.pet = pet;
    }

    public UUID getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public UUID getPet() {
        return pet;
    }
}
