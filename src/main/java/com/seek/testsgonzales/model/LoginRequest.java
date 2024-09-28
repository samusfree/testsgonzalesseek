package com.seek.testsgonzales.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LoginRequest", description = "LoginRequest Information")
public record LoginRequest(@Schema(name = "user", description = "user to login") String user,
                           @Schema(name = "password", description = "password to login") String password) {

}
