package com.smartRoute.services;

import com.smartRoute.dto.*;
import com.smartRoute.entity.*;
import com.smartRoute.repository.*;
import jakarta.transaction.*;
import org.springframework.stereotype.Service;

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
