package com.dotsageiv.homeconnect.core.presentation.dtos.requests;

import lombok.Builder;

@Builder
public record AddressRequest(String city, String state) {}