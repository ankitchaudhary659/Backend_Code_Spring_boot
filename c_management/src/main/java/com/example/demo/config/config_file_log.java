package com.example.demo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;


@Component
public class config_file_log extends AbstractRequestLoggingFilter {

    private static final String DEVICE_ID="DEVICE_ID";
    private static final String DEVICE_TYPE = "DEVICE_TYPE";

    Logger logger=LoggerFactory.getLogger(config_file_log.class);

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {

        logger.info("Before Request Starts :");
        logger.info("Request URL : "+request.getRequestURL().toString());
        logger.info("Request Type : "+request.getMethod());
        logger.info("Request URI : "+request.getRequestURI());
        logger.info("Remote Host : "+request.getRemoteHost());

    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {

        logger.info("After Request Starts :");
        logger.info("Before Request Starts :");
        logger.info("Request URL : "+request.getRequestURL().toString());
        logger.info("Request Type : "+request.getMethod());
        logger.info("Request URI : "+request.getRequestURI());
        logger.info("Remote Host : "+request.getRemoteHost());


    }
}

