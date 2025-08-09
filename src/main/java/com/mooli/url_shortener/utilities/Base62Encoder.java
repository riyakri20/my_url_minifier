package com.mooli.url_shortener.utilities;

import org.springframework.stereotype.Component;

@Component
public class Base62Encoder {
    public String Long2Base62(Long value) {
        StringBuilder base62Value = new StringBuilder();
        String mapping = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        while (value > 0) {
            base62Value.append(mapping.charAt((int) (value % 62)));
            value /= 62;
        }
        return base62Value.toString();
    }
}
