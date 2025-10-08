package it.smartcommunitylabdhub.filters;
// package com.example.demo.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Component
@Order(Integer.MIN_VALUE)
@WebFilter("/*")
public class SimpleLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest httpRequest) {
            System.out.println("----- Incoming Request -----");
            System.out.println("Method: " + httpRequest.getMethod());
            System.out.println("URI: " + httpRequest.getRequestURI());
            System.out.println("Query: " + httpRequest.getQueryString());
            System.out.println("--- Headers ---");

            Enumeration<String> headerNames = httpRequest.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                System.out.println(headerName + ": " + httpRequest.getHeader(headerName));
            }

            System.out.println("----------------------------");
        }

        // continua la catena
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // niente da inizializzare
    }

    @Override
    public void destroy() {
        // niente da distruggere
    }
}
