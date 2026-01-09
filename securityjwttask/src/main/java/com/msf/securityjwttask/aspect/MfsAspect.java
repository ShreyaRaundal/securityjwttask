package com.msf.securityjwttask.aspect;


import com.msf.securityjwttask.annotation.MFS;
import com.msf.securityjwttask.exception.InvalidTokenException;
import com.msf.securityjwttask.services.JwtService;
import com.msf.securityjwttask.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MfsAspect {

    private static final Logger log= LoggerFactory.getLogger(MfsAspect.class);

    private final JwtService jwtService;
    private final HttpServletRequest request;

    public MfsAspect(JwtService jwtService, HttpServletRequest request) {
        this.jwtService = jwtService;
        this.request = request;
    }

    @Before("@annotation(mfs)")
    public void validateToken(JoinPoint joinPoint, MFS mfs) {

        log.info("MFS validation started for method: {}", joinPoint.getSignature());

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.error("Authorization header missing");
            throw new InvalidTokenException("Token is missing");
        }

        String token = authHeader.substring(7);

        try {
            jwtService.extractUsername(token);
            log.info("Token validated successfully");
        } catch (Exception e) {
            log.error("Invalid token");
            throw new InvalidTokenException("Invalid or expired token");
        }
    }
}
