/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.config;

import com.vlemos.cursomc.services.DBService;
import com.vlemos.cursomc.services.EmailService;
import com.vlemos.cursomc.services.MockEmailService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author Vinicius Lemos
 */
@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDataBase() throws ParseException {
        dbService.instantiateDataBase();
        return true;
    }
    
    @Bean
   public EmailService emailService(){
       return new MockEmailService();
   } 
}
