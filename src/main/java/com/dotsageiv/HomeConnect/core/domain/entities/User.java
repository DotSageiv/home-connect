package com.dotsageiv.HomeConnect.core.domain.entities;

public record User(String cpf, String fullName,
                   String username, String password) {}