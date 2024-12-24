package com.aurionpro.loan.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        // Replace with your Cloudinary credentials
    	    String cloudName="dv04z9dvz";
    	   	String apiKey="995995214496642";
    	   	
    	   	String apiSecret="QaHadoryC13Yn9lpAaPiw_mu3_8";

        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }
    
    
   
   
}
