package com.citybank.filter;

import com.citybank.model.User;
import com.citybank.service.UserService;
import com.citybank.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.Enumeration;

//@Configuration
public class CustomFilter /*extends OncePerRequestFilter*/ {
//    private final PasswordEncoder passwordEncoder;
//    private final UserService userService;
//    private final CustomUserDetailsService customUserDetailsService;
//    public CustomFilter(UserService userService,PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService){
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//        this.customUserDetailsService = customUserDetailsService;
//    }
//
//    private User getUser(HttpServletRequest request){
//        String authString = null;
//        Enumeration<String> headersNameEnumeration= request.getHeaderNames();
//        while (headersNameEnumeration.hasMoreElements()){
//            String headerKey = headersNameEnumeration.nextElement();
//            if(headerKey.equalsIgnoreCase("Authorization")){
//                authString= request.getHeader(headerKey);
//                break;
//            }
//        }
//        if(authString != null && !authString.equals("")){
//            String[] authParts = authString.split("\\s+");
//            String authInfo = authParts[1];
//            byte[] bytes = DatatypeConverter.parseBase64Binary(authInfo);
//            String decodeAuth = new String(bytes);
//            String usernameAndPassword[] = decodeAuth.split(":");
//            if(usernameAndPassword[0] != null && usernameAndPassword[1] != null){
//                User user = userService.get(usernameAndPassword[0]);
//                if(passwordEncoder.matches(usernameAndPassword[1], user.getPassword())){
//                    return user;
//                }
//                return null;
//            }
//        }
//        return null;
//    }
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        try {
//            User user = getUser(httpServletRequest);
//            UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }catch (Exception e){
//
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
}
