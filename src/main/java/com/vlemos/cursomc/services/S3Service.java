/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.vlemos.cursomc.services.expections.FileException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vinicius.lemos
 */

@Service
public class S3Service {
    
    private Logger LOG = LoggerFactory.getLogger(S3Service.class);
    
    @Autowired
    private AmazonS3 s3Client;
      
     @Value("${s3.bucket}")
    private String bucketName;
    
    public URI uploadFile(MultipartFile multiPartFile) {
        try {
            String fileName = multiPartFile.getOriginalFilename();
            InputStream is = multiPartFile.getInputStream();
            String contentType = multiPartFile.getContentType();
            return uploadFile(is, fileName, contentType);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(S3Service.class.getName()).log(Level.SEVERE, null, ex);
            throw new FileException("Erro de IO " + ex.getMessage());

        }

    }
    
    public URI uploadFile(InputStream is, String fileName, String ContentType){
        
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(ContentType);
            LOG.info(" iniciando Upload ");
            s3Client.putObject(bucketName, fileName, is, meta);
            LOG.info(" Upload Finalizado ");
            return s3Client.getUrl(bucketName, fileName).toURI();
            
        } catch (URISyntaxException ex) {
            java.util.logging.Logger.getLogger(S3Service.class.getName()).log(Level.SEVERE, null, ex);
            throw new FileException("Erro convertendo URL para URI");
        }

    }

}
