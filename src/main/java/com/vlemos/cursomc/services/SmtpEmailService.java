/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;

import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author vinicius.lemos
 */
public class SmtpEmailService extends AbstractEmailService{

    @Autowired
    private MailSender mailSender;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    
     private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
    
    @Override
    public void sendEmail(SimpleMailMessage msg) {
        
        LOG.info("Enviando Email ... ");
        mailSender.send(msg);
        LOG.info("Email Enviado");
        
    }
    
    public void sendHtmlEmail(MimeMessage msg){
        LOG.info("Simulando o Envio de Email");
        javaMailSender.send(msg);
        LOG.info("Email Enviado");
        
    }
        

    
}
