package com.test.TestSpringBoot.controller;

import com.test.TestSpringBoot.exception.AlreadyExistException;
import com.test.TestSpringBoot.exception.AucationNotExitsException;
import com.test.TestSpringBoot.exception.BidIsLesserException;
import com.test.TestSpringBoot.exception.UserNotExitsException;
import com.test.TestSpringBoot.model.dto.request.AucationDto;
import com.test.TestSpringBoot.model.dto.request.UserDto;
import com.test.TestSpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/createUser")
    public ResponseEntity createUser(@RequestBody  UserDto userDto)
   {
       try{
           return new ResponseEntity(Optional.of(userService.createUser(userDto)), HttpStatus.CREATED);
       }catch (AlreadyExistException e) {
           return new ResponseEntity(Optional.of(e),HttpStatus.ALREADY_REPORTED);
       }
   }

   @PostMapping("/createAucation")
   public ResponseEntity createAucation(@RequestBody  AucationDto aucationDto)
   {
       try{
           return new ResponseEntity(Optional.of(userService.createAucation(aucationDto)), HttpStatus.CREATED);
       }catch (UserNotExitsException | BidIsLesserException  e) {
           return new ResponseEntity(Optional.of(e),HttpStatus.ALREADY_REPORTED);
       }
   }

   @PatchMapping("/updateBid/{newBid}/{userId}/{product}")
   public ResponseEntity updateBid(@PathVariable  float newBid,@PathVariable int userid,@PathVariable int product)
   {
       try{
           return new ResponseEntity(Optional.of(userService.updateBid(newBid,userid,product)), HttpStatus.CREATED);
       }catch (AucationNotExitsException e) {
           return new ResponseEntity(Optional.of(e),HttpStatus.NOT_FOUND);
       }
   }
}
