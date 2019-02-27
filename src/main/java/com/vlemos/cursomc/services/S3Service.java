/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    
    public void uploadFile(String localFilePath){
        try {
            File file = new File(localFilePath);
            LOG.info(" iniciando Upload ");
            s3Client.putObject(new PutObjectRequest(bucketName, "teste.PNG", file));
            LOG.info(" Upload Finalizado ");
        } catch (AmazonServiceException e) {
            LOG.info("AmazonServiceException " + e.getMessage());
            LOG.info(" Status Code  : " + e.getErrorCode());
            
        }catch (AmazonClientException e ){
            LOG.info("AmazonClientException " + e.getMessage());
            
        }

         
    }
    
}
