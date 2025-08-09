package com.mooli.url_shortener.dtos;

public record RegisterDto(
        String username,
        String emailAddress,
        String password
) {
}
