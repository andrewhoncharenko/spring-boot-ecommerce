package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.payload.AddressResponse;
import com.ecommerce.project.service.AddressService;
import com.ecommerce.project.util.AuthUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    AddressService addressService;
    @Autowired
    AuthUtils authUtils;

    @GetMapping("/admin/addresses")
    public ResponseEntity<AddressResponse> getAllAddresses(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
        AddressResponse addressResponse = addressService.getAddresses(pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }
    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long addressId) {
        return new ResponseEntity<>(addressService.getAddressById(addressId), HttpStatus.OK);
    }
    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddresses() {
        User user = authUtils.loggedInUser();
        List<AddressDTO> addressList = addressService.getUserAddresses(user);

        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }
    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> addAddress(@Valid @RequestBody AddressDTO addressDTO) {
        User user = authUtils.loggedInUser();
        AddressDTO creratedAddressDTO = addressService.createAddress(addressDTO, user);

        return new ResponseEntity<>(creratedAddressDTO, HttpStatus.CREATED);
    }
    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@Valid @RequestBody AddressDTO addressDTO,
                                                    @PathVariable Long addressId) {
        AddressDTO updatedAddressDTO = addressService.updateAddress(addressId, addressDTO);

        return new ResponseEntity<>(updatedAddressDTO, HttpStatus.OK);
    }
}
