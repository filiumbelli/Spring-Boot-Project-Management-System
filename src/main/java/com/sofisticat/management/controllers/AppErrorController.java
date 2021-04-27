package com.sofisticat.management.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AppErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest httpServletRequest) {
        Object attribute = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (attribute != null) {
            Integer integer = Integer.valueOf(attribute.toString());
            if (integer == HttpStatus.NOT_FOUND.value()) {
                return "errorpages/error404";
            } else if (integer == HttpStatus.BAD_GATEWAY.value()) {
                return "errorpages/error500";
            } else if (integer == HttpStatus.FORBIDDEN.value()) {
                return "errorpages/error403";
            }
        }
        return "errorpages/error";

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
