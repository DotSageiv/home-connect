package com.dotsageiv.homeconnect.core.presentation.resources;

import com.dotsageiv.homeconnect.core.domain.interfaces.AddressService;
import com.dotsageiv.homeconnect.core.presentation.dtos.mappers.AddressDTOMapper;
import com.dotsageiv.homeconnect.core.presentation.dtos.requests.AddressRequest;
import com.dotsageiv.homeconnect.core.presentation.dtos.responses.AddressResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/addresses")
@AllArgsConstructor
public class AddressResource {
    private final AddressService service;
    private final AddressDTOMapper mapper;

    @PostMapping("/{userId}")
    public ResponseEntity<AddressResponse> create(@Valid @PathVariable Long userId,
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
    public ResponseEntity<AddressResponse> getById(@PathVariable Long addressId,
                                                   @PathVariable Long userId) {
        var mappedResponse = mapper
                .toResponse(service.getById(addressId, userId));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mappedResponse);
    }

    @GetMapping("/{userId}")
    public List<AddressResponse> getAll(@PathVariable Long userId) {
        var domainObjs = service
                .getAll(userId)
                .spliterator();

        return StreamSupport.stream(domainObjs, false)
                .map(mapper::toResponse)
                .toList();
    }

    @PutMapping("/{addressId}/{userId}")
    public ResponseEntity<AddressResponse> updateById(@Valid @PathVariable Long addressId,
                                                      @PathVariable Long userId,
                                                      @RequestBody AddressRequest request) {
        var mapperDomainObj = mapper
                .toDomainObj(request);

        var updatedDomainObj = service
                .updateById(addressId, userId, mapperDomainObj);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResponse(updatedDomainObj));
    }

    @DeleteMapping("/{addressId}/{userId}")
    public ResponseEntity<?> deleteById(@PathVariable Long addressId,
                                        @PathVariable Long userId) {
        service.deleteById(addressId, userId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}