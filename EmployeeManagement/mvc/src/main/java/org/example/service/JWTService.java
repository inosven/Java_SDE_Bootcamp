package org.example.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.model.Role;
import org.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final static String SECRETE_KEY = System.getProperty("jwt.secret.key");
    private final static long EXPIRATION_TIME = 86400000;
    private final static String ISSUER = "FST.um.edu.mo";

    public String generateToken(User user) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecreteBytes = DatatypeConverter.parseBase64Binary(SECRETE_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecreteBytes, signatureAlgorithm.getJcaName());
        //claims = payload
        Claims claims = Jwts.claims();
        claims.setId(String.valueOf(user.getId()));
        claims.setSubject(user.getName());
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        List<Role> roles = user.getRoles();
        String allowedReadResources = "";
        String allowedCreateResources = "";
        String allowedUpdateResources = "";
        String allowedDeleteResources = "";

        String allowedResource = roles.stream().map(role -> role.getAllowedResource()).collect(Collectors.joining(","));
        claims.put("allowedResources", allowedResource);
        logger.info("allowedResource = {}", allowedResource);


        for (Role role : roles) {
            if (role.isAllowedRead())
                allowedReadResources = String.join(role.getAllowedResource(), allowedReadResources, ",");
            if (role.isAllowedCreate())
                allowedCreateResources = String.join(role.getAllowedResource(), allowedCreateResources, ",");
            if (role.isAllowedUpdate())
                allowedUpdateResources = String.join(role.getAllowedResource(), allowedUpdateResources, ",");
            if (role.isAllowedDelete())
                allowedDeleteResources = String.join(role.getAllowedResource(), allowedDeleteResources, ",");
        }

        logger.info("======, allowedReadResources = {}", allowedReadResources);
        logger.info("======, allowedCreateResources = {}", allowedCreateResources);
        logger.info("======, allowedUpdateResources = {}", allowedUpdateResources);
        logger.info("======, allowedDeleteResources = {}", allowedDeleteResources);


        claims.put("allowedReadResources", allowedReadResources.replaceAll(",$", ""));
        claims.put("allowedCreateResources", allowedCreateResources.replaceAll(",$", ""));
        claims.put("allowedUpdateResources", allowedUpdateResources.replaceAll(",$", ""));
        claims.put("allowedDeleteResources", allowedDeleteResources.replaceAll(",$", ""));
        // JWT claims
        JwtBuilder builder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm, signingKey);

        //builds JWT and serialize it to a compact, URL-sate string, generate token

        String generateToken = builder.compact();
        return generateToken;
    }

    public Claims decodeToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRETE_KEY))
                .parseClaimsJws(token).getBody();

        logger.info("Claims:" + claims.toString());
        return claims;

    }
}
