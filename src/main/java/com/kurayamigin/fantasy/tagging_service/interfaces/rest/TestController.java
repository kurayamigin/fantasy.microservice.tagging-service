package com.kurayamigin.fantasy.tagging_service.interfaces.rest;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("test")
public class TestController {

    private final MessageSource messageSource;

    public TestController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping
    public String test(@RequestHeader("Accept-Language") Locale locale) {
        return messageSource.getMessage("hello", null, locale);
    }
}
