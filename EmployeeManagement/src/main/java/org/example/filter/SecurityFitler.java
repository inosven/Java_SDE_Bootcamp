package org.example.filter;

import io.jsonwebtoken.Claims;
import org.example.model.Role;
import org.example.model.User;
import org.example.service.JWTService;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SecurityFitler implements Filter {
    private Logger logger = LoggerFactory.getLogger(LogFilter.class);
    private static final Set<String> IGNORED_PATH = new HashSet<String>(Arrays.asList("/auth"));
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Start to do authentication");

        HttpServletRequest req = (HttpServletRequest)servletRequest;
        int statusCode = authorization(req);
        if (statusCode == HttpServletResponse.SC_ACCEPTED) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse)servletResponse).sendError(statusCode);
        }
    }



    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private int authorization(HttpServletRequest req) {
    int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        String uri = req.getRequestURI();
        if (IGNORED_PATH.contains(uri)) {
            return HttpServletResponse.SC_ACCEPTED;
        }


        try {
            String token = req.getHeader("Authorization").replaceAll("^(.*?)", "");
            if (token.isEmpty())
                return statusCode;

            Claims claims = jwtService.decodeToken(token);
            logger.info("========== after parsing JWT token, claims.getId() = {}", claims.getId());

            if (claims.getId()!= null) {
                User u = userService.getUserbyId(Long.valueOf(claims.getId()));
                if (u != null) {
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                }
            }


            String verb = req.getMethod();
            String allowedResources = "";


            switch (verb) {
                case "GET":
                    allowedResources = (String) claims.get("allowedResources");
                    break;
                case "POST":
                    allowedResources = (String) claims.get("allowedCreateResources");
                    break;
                case "PUT":
                    allowedResources = (String) claims.get("allowedUpdateResources");
                    break;
                case "DELETE":
                    allowedResources = (String) claims.get("allowedDeleteResources");
            }

            for (String s : allowedResources.split(",")) {
                if (uri.trim().toLowerCase().startsWith(s.trim().toLowerCase())) {
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                    break;
                }
            }


        } catch (Exception e) {
            logger.error("Cannot get token");
        }
        return statusCode;
    }
}
