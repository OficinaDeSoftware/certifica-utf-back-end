package com.OficinaDeSoftware.EmissorCertificadosBackend.config;

import com.OficinaDeSoftware.EmissorCertificadosBackend.annotations.FeignRetryable;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public Retryer feignRetryer() {
        return new FeignRetryable.AnnotationRetryer( new Retryer.Default( 1000, 10000, 3 ) );
    }

}
