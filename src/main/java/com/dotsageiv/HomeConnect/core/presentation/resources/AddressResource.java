package com.dotsageiv.HomeConnect.core.presentation.resources;

import com.dotsageiv.HomeConnect.core.presentation.dtos.mappers.AddressDTOMapper;
import com.dotsageiv.HomeConnect.core.presentation.dtos.requests.AddressRequest;
import com.dotsageiv.HomeConnect.core.presentation.dtos.responses.AddressResponse;
import com.dotsageiv.HomeConnect.infrastructure.gateway.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/addresses")
public class AddressResource {
    private final AddressDTOMapper mapper;
    private final AddressService service;

    public AddressResource(AddressDTOMapper mapper, AddressService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<AddressResponse> create(@PathVariable UUID userId,
                                                  @RequestBody AddressRequest request) {
        var mappedDomainObj = mapper
                .toDomainObj(request);

        var savedDomainObj = service
                .create(userId, mappedDomainObj);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(savedDomainObj));
    }

    @GetMapping("/{addressId}/{userId}")
    public ResponseEntity<AddressResponse> getById(@PathVariable UUID addressId,
                                                   @PathVariable UUID userId) {
        var mappedResponse = mapper
                .toResponse(service.getById(userId, addressId));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mappedResponse);
    }

    @GetMapping("/{userId}")
    public List<AddressResponse> getAll(@PathVariable UUID userId) {
        var domainObjs = service
                .getAll(userId)
                .spliterator();

        return StreamSupport.stream(domainObjs, false)
                .map(mapper::toResponse)
                .toList();
    }

    @PutMapping("/{addressId}/{userId}")
    public ResponseEntity<AddressResponse> updateById(@PathVariable UUID addressId,
                                                      @PathVariable UUID userId,
                                                      @RequestBody AddressRequest request) {
        var mapperDomainObj = mapper
                .toDomainObj(request);

        var updatedDomainObj = service
                .updateById(userId, addressId, mapperDomainObj);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResponse(updatedDomainObj));
    }

    @DeleteMapping("/{addressId}/{userId}")
    public ResponseEntity<?> deleteById(@PathVariable UUID addressId,
                                        @PathVariable UUID userId) {
        service.deleteById(userId, addressId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}