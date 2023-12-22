package com.car.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.Reservations;
import com.car.service.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private final ReservationsService reservationsService;

    @Autowired
    public ReservationsController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    // Get reservation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservations> getReservationById(@PathVariable Integer id) {
        Reservations reservation = reservationsService.getById(id);
        return ResponseEntity.ok(reservation);
    }

    // Create a new reservation
    @PostMapping
    public ResponseEntity<Reservations> createReservation(@RequestBody Reservations reservation) {
        reservationsService.save(reservation);
        return ResponseEntity.ok(reservation);
    }

    // Update an existing reservation
    @PutMapping("/{id}")
    public ResponseEntity<Reservations> updateReservation(@PathVariable Integer id, @RequestBody Reservations reservationDetails) {
        reservationDetails.setReservationid(id); // Set the ID from the path variable
        reservationsService.updateById(reservationDetails);
        return ResponseEntity.ok(reservationDetails);
    }

    // Delete a reservation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Integer id) {
        reservationsService.removeById(id);
        return ResponseEntity.ok().build();
    }

    // Get all reservations
    @GetMapping("/all")
    public ResponseEntity<List<Reservations>> getAllReservations() {
        List<Reservations> reservations = reservationsService.list();
        return ResponseEntity.ok(reservations);
    }

    // Get reservations with pagination
    @GetMapping("/page")
    public ResponseEntity<Page<Reservations>> getReservationsWithPagination(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Reservations> page = reservationsService.getReservationsWithPagination(pageNum, pageSize);
        return ResponseEntity.ok(page);
    }
}
