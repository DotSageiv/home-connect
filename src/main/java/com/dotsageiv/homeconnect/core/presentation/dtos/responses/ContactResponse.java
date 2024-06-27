package com.dotsageiv.homeconnect.core.presentation.dtos.responses;

import lombok.Builder;

@Builder
public record ContactResponse(Long userId, Long contactId,
                              String email, String phoneNumber) {}