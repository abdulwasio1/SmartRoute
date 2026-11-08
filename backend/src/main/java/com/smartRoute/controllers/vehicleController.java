package com.smartRoute.controllers;
import com.smartRoute.dto.*;
import com.smartRoute.entity.*;
import com.smartRoute.services.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/api/vehicle")
public class vehicleController {
    private final vehicleService service;

    public vehicleController(vehicleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<vehicleResponse> createVehicle(@Valid  @RequestBody vehicleRequest request) {
        vehicleResponse response = service.createVehicle(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }



}
