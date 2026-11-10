package com.smartRoute.services;

import com.smartRoute.dto.*;
import com.smartRoute.entity.*;
import com.smartRoute.repository.*;
import jakarta.transaction.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class vehicleService {
    vehicleRepo vehicle_Repo;

    public vehicleService(vehicleRepo vehicle_Repo) {
        this.vehicle_Repo = vehicle_Repo;
    }

    @Transactional
    public vehicleResponse createVehicle(vehicleRequest request) {

        validateVehicle(request); //check the request is correct or not

        vehicle vehicle = new vehicle(); //to convert request into vehicle object we create a empty object to save it

        mapRequestToEntity(request, vehicle); //convert that request into that provided vehicle object
        //so after that vehicle = some vehicle data that user provided

        vehicle savedVehicle = vehicle_Repo.save(vehicle); //save that vehicle object, why we convert request into vehicle object bcz database methods require vehicle object as parameter not request

        return mapToResponse(savedVehicle); //convert back that object into request bcz our controller again need JSON file to send to the UI
    }

    private vehicleResponse mapToResponse(vehicle savedVehicle) {
        return new vehicleResponse(
                savedVehicle.getId(),
                savedVehicle.getName(),
                savedVehicle.getType(),
                savedVehicle.getBatteryCapacityKwh(),
                savedVehicle.getEnergyConsumptionKwhPerKm(),
                savedVehicle.getFuelTankCapacityLitres(),
                savedVehicle.getFuelConsumptionKmPerLitres(),
                savedVehicle.getFuelType()
        );
    }

    private void validateVehicle(vehicleRequest request) {

        //Optional checks - cross verification
        if(request.getType() == null) {
            throw new IllegalArgumentException("Vehicle is required");
        }
        if (request.getName() == null) {
            throw new IllegalArgumentException("Vehicle name is required");
        }
        if(isNegative(request.getBatteryCapacityKwh()) ||
                isNegative(request.getFuelTankCapacityLitres()) ||
                isNegative(request.getEnergyConsumptionKwhPerKm()) ||
                isNegative(request.getFuelConsumptionKmPerLitre())) {
            throw  new IllegalArgumentException("Vehicle Specifications can't be negative");
        }

        // for EV - we reject petrol values
        if (request.getType() == vehicleType.EV){
            if (request.getBatteryCapacityKwh() == null || request.getBatteryCapacityKwh().compareTo(BigDecimal.ZERO) <= 0){
                throw new IllegalArgumentException("Battery Capacity must be greater than 0");
            }
            if (request.getEnergyConsumptionKwhPerKm() == null ||
                    request.getEnergyConsumptionKwhPerKm().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Energy consumption must be greater than zero");
            }
            if (hasPositiveValue(request.getFuelTankCapacityLitres()) ||
                    hasPositiveValue(request.getFuelConsumptionKmPerLitre()) ||
                    (request.getFuelType() != null && !request.getFuelType().isBlank())) {
                throw new IllegalArgumentException("Electric vehicles cannot have fuel specifications");
            }
        }

        //for petrol - we reject ev values
        if (request.getType() == vehicleType.PETROL) {
            if (request.getFuelTankCapacityLitres() == null || request.getFuelTankCapacityLitres().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Fuel Tank Capacity must be greater than 0");
            }
            if (request.getFuelConsumptionKmPerLitre() == null || request.getFuelConsumptionKmPerLitre().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Fuel Consumption Km must be greater than 0");
            }
            if (request.getFuelType() == null || request.getFuelType().isBlank()) {
                throw new IllegalArgumentException("Fuel type is required");
            }
            if (hasPositiveValue(request.getBatteryCapacityKwh()) || hasPositiveValue(request.getEnergyConsumptionKwhPerKm())){
                throw new IllegalArgumentException("Petrol vehicles cannot have battery specifications");
            }
        }

    }

    private boolean isNegative(BigDecimal value) {
        return value != null &&
                value.compareTo(BigDecimal.ZERO) < 0;
    }

    private boolean hasPositiveValue(BigDecimal value) {
        return value != null &&
                value.compareTo(BigDecimal.ZERO) > 0;
    }

    private void mapRequestToEntity(vehicleRequest request, vehicle vehicle) {
        // that's why we use getters in request
        vehicle.setName(request.getName());
        vehicle.setType(request.getType());

        vehicle.setBatteryCapacityKwh(request.getBatteryCapacityKwh());
        vehicle.setEnergyConsumptionKwhPerKm(
                request.getEnergyConsumptionKwhPerKm()
        );

        vehicle.setFuelTankCapacityLitres(request.getFuelTankCapacityLitres());
        vehicle.setFuelConsumptionKmPerLitres(request.getFuelConsumptionKmPerLitre());
        vehicle.setFuelType(request.getFuelType());
    }
}
