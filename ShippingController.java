package com.kb.shop.controller;

import com.kb.shop.domain.ShippingInfo;
import com.kb.shop.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @PostMapping("/")
    public ResponseEntity<ShippingInfo> createShipping(@RequestBody ShippingInfo shippingInfo) {
        ShippingInfo created = shippingService.createShippingInfo(shippingInfo);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingInfo> getShipping(@PathVariable Long id) {
        Optional<ShippingInfo> shippingInfo = shippingService.getShippingInfo(id);
        return shippingInfo.map(info -> new ResponseEntity<>(info, HttpStatus.OK))
                           .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShippingInfo> updateStatus(@PathVariable Long id, @RequestParam String status) {
        ShippingInfo updated = shippingService.updateShippingStatus(id, status);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
