/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;


import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author vinicius.lemos
 */
public class MockEmailService extends AbstractEmailService{
    
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Simulando o Envio de Email");
        LOG.info(msg.toString());
        LOG.info("Email Enviado");
        
    }
    
     public void sendHtmlEmail(MimeMessage msg){
        LOG.info("Simulando o Envio de Email Html");
        LOG.info(msg.toString());
        LOG.info("Email Enviado");
         
     }
    
}
