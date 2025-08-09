package com.mooli.url_shortener.dtos;

public record LoginDto(
        String usernameOrEmailAddress,
        String password
) {
}
