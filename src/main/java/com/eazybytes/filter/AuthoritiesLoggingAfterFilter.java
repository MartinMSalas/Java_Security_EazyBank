package com.eazybytes.filter;

import jakarta.servlet.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthoritiesLoggingAfterFilter implements Filter {

    private final Logger LOG = Logger.getLogger(getClass().getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            LOG.info("User: " + authentication.getName() + " is susccessfully authenticated with authorities: " + authentication.getAuthorities().toString());
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
