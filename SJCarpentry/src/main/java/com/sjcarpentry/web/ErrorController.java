package com.sjcarpentry.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @GetMapping
    public String errorCheck(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value())
                return "page-not-found";

            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
                return "500-error";
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}


