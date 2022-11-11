package com.test.TestSpringBoot.controller;

import com.test.TestSpringBoot.exception.AlreadyExistException;
import com.test.TestSpringBoot.exception.UserNotExitsException;
import com.test.TestSpringBoot.exception.UserOrProductNotExitsException;
import com.test.TestSpringBoot.model.dto.request.ProductDto;
import com.test.TestSpringBoot.model.dto.request.VendorDto;
import com.test.TestSpringBoot.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class VendorController {
    @Autowired
    VendorService vendorService;

    @PostMapping("/createVendor")
    public ResponseEntity createVendor(@Valid @RequestBody VendorDto vendorDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(Optional.of(bindingResult.getFieldError("email").getDefaultMessage().toString()), HttpStatus.BAD_REQUEST);
        } else {
            try {
                return new ResponseEntity(Optional.of(vendorService.createVendor(vendorDto)), HttpStatus.CREATED);
            } catch (AlreadyExistException alreadyExistException) {
                return new ResponseEntity(Optional.of(alreadyExistException.getMessage()), HttpStatus.ALREADY_REPORTED);
            }
        }
    }

    @PostMapping("/createProduct")
    public ResponseEntity createProduct(@RequestBody ProductDto productDto) {
        try {
            return new ResponseEntity(Optional.of(vendorService.createProduct(productDto)), HttpStatus.CREATED);
        } catch (UserNotExitsException e) {
            return new ResponseEntity(Optional.of(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/updateProduct")
    public ResponseEntity updateProduct(@RequestBody ProductDto productDto) {
        try {
            return new ResponseEntity(Optional.of(vendorService.updateProduct(productDto)), HttpStatus.CREATED);
        } catch (UserNotExitsException e) {
            return new ResponseEntity(Optional.of(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/deleteProduct/{productid}/{userid}")
    public ResponseEntity deleteProduct(@PathVariable int productId,@PathVariable  int userId) {
        try {
            return new ResponseEntity(Optional.of(vendorService.deleteProduct(productId,userId)), HttpStatus.CREATED);
        } catch (UserOrProductNotExitsException userOrProductNotExitsException) {
            return new ResponseEntity(Optional.of(userOrProductNotExitsException.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}


