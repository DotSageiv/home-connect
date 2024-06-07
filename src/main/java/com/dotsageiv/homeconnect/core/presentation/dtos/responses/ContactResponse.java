package com.dotsageiv.homeconnect.core.presentation.dtos.responses;

import lombok.Builder;

@Builder
public record ContactResponse(String email, String phoneNumber) {}