package com.test.TestSpringBoot.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMaker {
  @Bean
   public ModelMapper getMapper()
  {
      return new ModelMapper();
  }
}
