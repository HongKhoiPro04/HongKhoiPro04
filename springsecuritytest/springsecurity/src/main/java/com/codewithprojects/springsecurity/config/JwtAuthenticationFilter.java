package com.codewithprojects.springsecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codewithprojects.springsecurity.service.JWTService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private JWTService jwtService;


    private UserInfoDetailsServices userServices;

    public JwtAuthenticationFilter(JWTService jwtService, UserInfoDetailsServices userServices) {
        this.jwtService = jwtService;
        this.userServices = userServices;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("///  "+request);
         String authHeader = request.getHeader("Authorization");
         String jwt;
         String userEmail;
        System.out.println("// Au // "+authHeader);

        if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader,"Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        jwt = authHeader.substring(7);
        System.out.println("// JWT //"+jwt);
        userEmail = jwtService.extractUserName(jwt);
        System.out.println("// User | "+userEmail);

        if(StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userServices.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(jwt, userDetails)){
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request,response);
    }
}
