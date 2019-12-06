package com.udacity.vehicles.domain.customcar;

import com.udacity.vehicles.domain.enums.Condition;
import com.udacity.vehicles.domain.enums.Currency;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class CustomCar {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @NotNull
    private double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Valid
    @Embedded
    private CustomCarDetails details = new CustomCarDetails();

    @Valid
    @Embedded
    private CustomLocation customLocation = new CustomLocation();


    public CustomLocation getCustomLocation() { return customLocation; }

    public void setCustomLocation(CustomLocation customLocation) { this.customLocation = customLocation; }

    public void setPrice(double price) { this.price = price; }

    public Currency getCurrency() { return currency; }

    public void setCurrency(Currency currency) { this.currency = currency; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public CustomCarDetails getDetails() {
        return details;
    }

    public void setDetails(CustomCarDetails details) {
        this.details = details;
    }

    public double getPrice() { return price; }
}
