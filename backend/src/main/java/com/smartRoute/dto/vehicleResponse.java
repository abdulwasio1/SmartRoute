package com.smartRoute.dto;

import com.smartRoute.entity.*;

import java.math.BigDecimal;

public class vehicleResponse {
    private int id;
    private String name;
    private vehicleType type;

    private BigDecimal batteryCapacityKwh;
    private BigDecimal energyConsumptionKwhPerKm;

    private BigDecimal fuelTankCapacityLitres;
    private BigDecimal fuelConsumptionKmPerLitre;

    private String fuelType;

    public vehicleResponse(int id, String name, vehicleType type,
                           BigDecimal batteryCapacityKwh, BigDecimal energyConsumptionKwhPerKm,
                           BigDecimal fuelTankCapacityLitres, BigDecimal fuelConsumptionKmPerLitre,
                           String fuelType) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.batteryCapacityKwh = batteryCapacityKwh;
        this.energyConsumptionKwhPerKm = energyConsumptionKwhPerKm;
        this.fuelTankCapacityLitres = fuelTankCapacityLitres;
        this.fuelConsumptionKmPerLitre = fuelConsumptionKmPerLitre;
        this.fuelType = fuelType;
    }

    public int getId() {
        return id;
    }

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
