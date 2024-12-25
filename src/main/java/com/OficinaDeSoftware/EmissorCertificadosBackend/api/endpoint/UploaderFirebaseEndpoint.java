package com.OficinaDeSoftware.EmissorCertificadosBackend.api.endpoint;

import com.OficinaDeSoftware.EmissorCertificadosBackend.annotations.FeignRetryable;
import com.OficinaDeSoftware.EmissorCertificadosBackend.api.response.UploaderImageResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("${uploader.firebase.host:UPLOADER}")
public interface UploaderFirebaseEndpoint {

    @PostMapping( value="/image", consumes = "multipart/form-data" )
    @Headers("Content-Type: multipart/form-data")
    @FeignRetryable
    ResponseEntity<UploaderImageResponse> image( @RequestPart("file") MultipartFile multipartFile );

}
