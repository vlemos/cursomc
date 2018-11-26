/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;

import com.vlemos.cursomc.domain.Pedido;
import java.util.Date;
import java.util.logging.Level;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



/**
 *
 * @author vinicius.lemos
 */
public abstract class AbstractEmailService implements EmailService {
    
    @Value("${default.sender}")
    private String sender;
    
    @Autowired
    private TemplateEngine templateEngine;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Override
    public void sendOrderConfirmationEmail(Pedido obj){
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(sm);
    }

    private SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido Confirmado ! Codigo : " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }
    
    protected String htmlFromTemplatePedido(Pedido obj){
        Context context = new Context();
        context.setVariable("pedido", obj);
        return templateEngine.process("email/confirmacaoPedido", context);
        
    }
    
    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido obj){
        MimeMessage mm;
        try {
            mm = prepareMimeMessageFromPedido(obj);
            sendHtmlEmail(mm);
        } catch (MessagingException ex) {
            sendOrderConfirmationEmail(obj);
        }
                
    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
        MimeMessage mimiMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimiMessage, true);
        mmh.setTo(obj.getCliente().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Pedido Confirmado! Codigo :" + obj.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(obj),true);
        return mimiMessage;
    }
    
}
