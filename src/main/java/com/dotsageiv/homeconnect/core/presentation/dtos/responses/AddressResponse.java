package com.dotsageiv.homeconnect.core.presentation.dtos.responses;

import lombok.Builder;

@Builder
public record AddressResponse(Long userId, Long addressId,
                              String city, String state) {}