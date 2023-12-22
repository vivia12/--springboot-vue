package com.car.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.LocationRentalsDTO;
import com.car.entity.Locations;
import com.car.service.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    private final LocationsService locationsService;

    @Autowired
    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    // Get all locations
    @GetMapping("/all")
    public ResponseEntity<List<Locations>> getAllLocations() {
        List<Locations> locations = locationsService.list();
        return ResponseEntity.ok(locations);
    }

    // Get locations with pagination
    @GetMapping("/page")
    public ResponseEntity<Page<Locations>> getLocationsWithPagination(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Locations> page = locationsService.getLocationsWithPagination(pageNum, pageSize);
        return ResponseEntity.ok(page);
    }

    // Create location
    @PostMapping
    public ResponseEntity<Locations> createLocation(@RequestBody Locations location) {
        locationsService.save(location);
        return ResponseEntity.ok(location);
    }

    // Update location
    @PutMapping("/{id}")
    public ResponseEntity<Locations> updateLocation(@PathVariable Integer id, @RequestBody Locations locationDetails) {
        locationDetails.setLocationId(id); // Set the ID from the path variable
        locationsService.updateById(locationDetails);
        return ResponseEntity.ok(locationDetails);
    }

    // Delete location
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable Integer id) {
        locationsService.removeById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/popular")
    public ResponseEntity<List<LocationRentalsDTO>> PopularLocations() {
        List<LocationRentalsDTO> locations = locationsService.PopularLocations();
        return ResponseEntity.ok(locations);
    }
}
