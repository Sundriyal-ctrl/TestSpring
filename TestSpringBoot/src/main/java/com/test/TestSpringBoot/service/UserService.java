package com.test.TestSpringBoot.service;

import com.test.TestSpringBoot.entity.AucationEntity;
import com.test.TestSpringBoot.entity.ProductEntity;
import com.test.TestSpringBoot.entity.UserEntity;
import com.test.TestSpringBoot.exception.AlreadyExistException;
import com.test.TestSpringBoot.exception.AucationNotExitsException;
import com.test.TestSpringBoot.exception.BidIsLesserException;
import com.test.TestSpringBoot.exception.UserNotExitsException;
import com.test.TestSpringBoot.model.dto.request.AucationDto;
import com.test.TestSpringBoot.model.dto.request.UserDto;
import com.test.TestSpringBoot.repository.AucationRepository;
import com.test.TestSpringBoot.repository.ProductRepository;
import com.test.TestSpringBoot.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
   UserRepository userRepository;

    @Autowired
   AucationRepository aucationRepository;
    @Autowired
    ProductRepository productRepository;
    public UserDto createUser(UserDto userDto) throws AlreadyExistException {
      if(!userRepository.existsByEmailAndPhone(userDto.getEmail(),userDto.getNumber()))
      {
          UserEntity userEntity=this.modelMapper.map(userDto,UserEntity.class);
          UserDto userDto1=this.modelMapper.map(userRepository.save(userEntity),UserDto.class);
          return userDto1;
      }
      else {
          throw new AlreadyExistException("User with email "+userDto.getEmail()+" is already Exits");
      }
   }

   public UserEntity createAucation(AucationDto aucationDto) throws BidIsLesserException, UserNotExitsException {
     if(userRepository.existsById(aucationDto.getUserid()) &&productRepository.existsById(aucationDto.getProductid()))
     {
         ProductEntity productEntity=productRepository.findById(aucationDto.getProductid()).get();
         if(productEntity.getBid()>aucationDto.getBidmoney())
         {
             UserEntity userEntity=userRepository.getById(aucationDto.getUserid());
             AucationEntity aucationEntity=this.modelMapper.map(aucationDto,AucationEntity.class);
             aucationEntity.setProductEntity(productEntity);
             aucationEntity.setUserEntity(userEntity);
             productEntity.getAucationEntityList().add(aucationEntity);
             userEntity.getAucationEntityList().add(aucationEntity);
             userRepository.save(userEntity);
             productRepository.save(productEntity);
             return userEntity;
         }
         else {
             throw new BidIsLesserException("Your Bid is lesser than product bid");
         }
     }
     else {
         throw new UserNotExitsException("user not exist for product id is wrong");
     }
   }

   public AucationEntity updateBid(float newBid,int userid,int product) throws AucationNotExitsException {
      AucationEntity aucationEntity=aucationRepository.findByUseridEqualsAndProductidEquals(userid,product);
      if(aucationEntity==null)
      {
        throw new AucationNotExitsException("This Aucation not exits");
      }
      else {
          aucationEntity.setBidmoney(aucationEntity.getBidmoney()+newBid);
          return aucationRepository.save(aucationEntity);
      }
   }

}
