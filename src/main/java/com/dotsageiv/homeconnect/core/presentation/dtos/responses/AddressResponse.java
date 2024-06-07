package com.dotsageiv.homeconnect.core.presentation.dtos.responses;

import lombok.Builder;

@Builder
public record AddressResponse(String city, String state) {}