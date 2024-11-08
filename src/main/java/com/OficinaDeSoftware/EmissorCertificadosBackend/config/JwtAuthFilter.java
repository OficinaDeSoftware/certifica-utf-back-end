package com.OficinaDeSoftware.EmissorCertificadosBackend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthenticationProvider userAuthenticationProvider;

    public JwtAuthFilter(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal( 
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain ) throws ServletException, IOException {
                
        String header = httpServletRequest.getHeader( HttpHeaders.AUTHORIZATION );

        if ( header == null ) {
            filterChain.doFilter( httpServletRequest, httpServletResponse );
            return;
        }

        try {
            SecurityContextHolder.getContext().setAuthentication( userAuthenticationProvider.validateToken( header ) );
        } catch (RuntimeException e) {
            log.error( "Fail jwt authentication [WHAT] {}", e.getMessage() );
            SecurityContextHolder.clearContext();
            throw e;
        }
    }
}