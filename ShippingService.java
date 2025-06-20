package com.kb.shop.service;

import com.kb.shop.domain.ShippingInfo;
import com.kb.shop.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    public ShippingInfo createShippingInfo(ShippingInfo shippingInfo) {
        return shippingRepository.save(shippingInfo);
    }

    public Optional<ShippingInfo> getShippingInfo(Long id) {
        return shippingRepository.findById(id);
    }

    public ShippingInfo updateShippingStatus(Long id, String status) {
        Optional<ShippingInfo> optional = shippingRepository.findById(id);
        if (optional.isPresent()) {
            ShippingInfo info = optional.get();
            info.setShippingStatus(status);
            return shippingRepository.save(info);
        }
        return null;
    }
}
