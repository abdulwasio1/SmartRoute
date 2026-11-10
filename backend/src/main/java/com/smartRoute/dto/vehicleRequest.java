package com.smartRoute.dto;

import com.smartRoute.entity.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class vehicleRequest {
    //Request means we are creating table record
    //No id will be provided bcz we are requesting - database will create by own sequence wise

    @NotBlank(message = "Vehicle Name can not be null")
    private String name;
    @NotNull(message = "Vehicle type can not be null")
    private vehicleType type;

    @DecimalMin(value = "0.0", inclusive = false , message = "Value must be greater than 0")
    private BigDecimal batteryCapacityKwh;

    @DecimalMin(value = "0.0", inclusive = false , message = "Value must be greater than 0")
    private BigDecimal energyConsumptionKwhPerKm;

    @DecimalMin(value = "0.0", inclusive = false , message = "Value must be greater than 0")
    private BigDecimal fuelTankCapacityLitres;

    @DecimalMin(value = "0.0", inclusive = false , message = "Value must be greater than 0")
    private BigDecimal fuelConsumptionKmPerLitre;

    private String  fuelType;

    //WHY Setters : bcz we are setting the values java code that are extracted from JSON

    public void setName(String name) {
        this.name = name;
    }

    public void setType(vehicleType type) {
        this.type = type;
    }

    public void setBatteryCapacityKwh(BigDecimal batteryCapacityKwh) {
        this.batteryCapacityKwh = batteryCapacityKwh;
    }

    public void setEnergyConsumptionKwhPerKm(BigDecimal energyConsumptionKwhPerKm) {
        this.energyConsumptionKwhPerKm = energyConsumptionKwhPerKm;
    }

    public void setFuelTankCapacityLitres(BigDecimal fuelTankCapacityLitres) {
        this.fuelTankCapacityLitres = fuelTankCapacityLitres;
    }

    public void setFuelConsumptionKmPerLitre(BigDecimal fuelConsumptionKmPerLitre) {
        this.fuelConsumptionKmPerLitre = fuelConsumptionKmPerLitre;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }


    //Why Getters : Bcz when we set the values also saved in database,
    // after i have to send JSON request back to controller so for that i need again the values from that request

    public String getName() {
        return name;
    }

    public vehicleType getType() {
        return type;
    }

    public BigDecimal getBatteryCapacityKwh() {
        return batteryCapacityKwh;
    }

    public BigDecimal getEnergyConsumptionKwhPerKm() {
        return energyConsumptionKwhPerKm;
    }

    public BigDecimal getFuelTankCapacityLitres() {
        return fuelTankCapacityLitres;
    }

    public BigDecimal getFuelConsumptionKmPerLitre() {
        return fuelConsumptionKmPerLitre;
    }

    public String getFuelType() {
        return fuelType;
    }



}
