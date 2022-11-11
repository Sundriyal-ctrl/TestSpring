package com.test.TestSpringBoot.service;

import com.test.TestSpringBoot.entity.*;
import com.test.TestSpringBoot.exception.AlreadyExistException;
import com.test.TestSpringBoot.exception.UserNotExitsException;
import com.test.TestSpringBoot.exception.UserOrProductNotExitsException;
import com.test.TestSpringBoot.model.dto.request.ProductDto;
import com.test.TestSpringBoot.model.dto.request.VendorDto;
import com.test.TestSpringBoot.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class VendorService {
   @Autowired
   ModelMapper modelMapper;
   @Autowired
   VendorRepository vendorRepository;
   @Autowired
   ProductRepository productRepository;

   @Autowired
   UserRepository userRepository;
   @Autowired
   AucationRepository aucationRepository;

   @Autowired
   BuyerRepository buyerRepository;
   public VendorDto createVendor(VendorDto vendorDto) throws AlreadyExistException {
      VendorEntity vendorEntity=this.modelMapper.map(vendorDto,VendorEntity.class);
      if(!vendorRepository.existsByEmailAndPhone(vendorDto.getEmail(),vendorDto.getPhone()))
      {
         VendorDto vendorDto1=this.modelMapper.map(vendorRepository.save(vendorEntity),VendorDto.class);
         return vendorDto1;
      }
      else {
        throw new AlreadyExistException("this user with email "+vendorDto.getEmail()+" is already Exits");
      }
   }

   public ProductDto createProduct(ProductDto productDto) throws UserNotExitsException {
      if(vendorRepository.existsById(productDto.getUserid())) {
         ProductEntity productEntity = this.modelMapper.map(productDto, ProductEntity.class);
         ProductDto productDto1 = this.modelMapper.map(productRepository.save(productEntity), ProductDto.class);
         return productDto1;
      }
      else {
         throw new UserNotExitsException("User with id "+productDto.getUserid()+" not found");
      }
   }

   public ProductDto updateProduct(ProductDto productDto) throws UserNotExitsException {
      if(vendorRepository.existsById(productDto.getUserid())) {
         ProductEntity productEntity = this.modelMapper.map(productDto, ProductEntity.class);
         ProductDto productDto1 = this.modelMapper.map(productRepository.save(productEntity), ProductDto.class);
         return productDto1;
      }
      else {
         throw new UserNotExitsException("User with id "+productDto.getUserid()+" not found");
      }
   }

   public HashMap<String,String> deleteProduct(int productid,int userId) throws UserOrProductNotExitsException {
      if(vendorRepository.existsById(userId) && productRepository.existsById(productid))
      {
         ProductEntity productEntity=productRepository.findById(productid).get();
         productRepository.delete(productEntity);
         if(!productRepository.existsById(productid))
         {
            HashMap<String,String> status=new HashMap<>();
            status.put("message","Product deleted Successfully");
            return status;
         }
         else{
            HashMap<String,String> status=new HashMap<>();
            status.put("message","Product Not deleted");
            return status;
         }
      }
      else {
          throw  new UserOrProductNotExitsException("user or product not exits");
      }
   }

   public AucationEntity getTopAucation()
   {
      Sort sort=Sort.by("bidmoney");
      List<AucationEntity> list=aucationRepository.findAll(sort);
      if(list.size()>0) {
         AucationEntity aucationEntity = list.get(0);
         BuyerEntity buyerEntity=new BuyerEntity();
         buyerEntity.setProductId(list.get(0).getProductid());
         buyerEntity.setUserid(list.get(0).getUserid());
         buyerRepository.save(buyerEntity);
         return aucationEntity;
      }
      return null;
   }
}
