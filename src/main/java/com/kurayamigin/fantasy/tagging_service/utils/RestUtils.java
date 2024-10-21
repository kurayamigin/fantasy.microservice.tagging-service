package com.kurayamigin.fantasy.tagging_service.utils;

import org.springframework.web.context.request.WebRequest;

public class RestUtils {
    public static String getPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }
}
