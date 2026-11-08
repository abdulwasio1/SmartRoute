package com.smartRoute.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Audited;

import java.math.BigDecimal;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private vehicleType type;

    private BigDecimal batteryCapacityKwh;
    private BigDecimal energyConsumptionKwhPerKm;
    private BigDecimal fuelTankCapacityLitres;
    private BigDecimal fuelConsumptionKmPerLitres;
    private String fuelType;
}
