/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;

import com.vlemos.cursomc.services.expections.FileException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vinicius.lemos
 */

@Service
public class ImageService {
        public BufferedImage getJpgFromFile(MultipartFile uploadedFile){
            String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename()); //péga extensão do Arquivo original
            if(!"png".equals(ext) && !"jpg".equals(ext)){
                throw new FileException("Somente Imagens PNG e JPG são permitidas");
            }
            try {
                BufferedImage img= ImageIO.read(uploadedFile.getInputStream());
                if("png".equals(ext)){ // se a imagem for PNG então converte para JPG
                    img = pngToJpg(img);
                }
                return img;
            } catch (IOException ex) {
                Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE, null, ex);
                throw new FileException("Erro ao ler o arquivo");
            }
        }

    public BufferedImage pngToJpg(BufferedImage img) {
        BufferedImage jpgImage = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB );
        jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
        return jpgImage;
    }
    
    public InputStream getInputStream(BufferedImage img, String extension){
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            throw new FileException("Erro ao ler o arquivo");
        }
    }
    
    public BufferedImage cropSquare(BufferedImage sourceImg){
        int min = (sourceImg.getHeight() < sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
        return Scalr.crop(sourceImg, 
                (sourceImg.getWidth()/2) - (min/2), 
                (sourceImg.getHeight()/2) - (min/2),
                min,
                min);
    }
    
    public BufferedImage resize(BufferedImage sourceImg, int size){
        return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
    }
}
