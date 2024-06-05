package com.dotsageiv.HomeConnect.core.presentation.resources;

import com.dotsageiv.HomeConnect.core.presentation.dtos.mappers.UserDTOMapper;
import com.dotsageiv.HomeConnect.core.presentation.dtos.requests.UserRequest;
import com.dotsageiv.HomeConnect.core.presentation.dtos.responses.UserResponse;
import com.dotsageiv.HomeConnect.infrastructure.gateway.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/users")
public class UserResource {
    private final UserService service;
    private final UserDTOMapper mapper;

    public UserResource(UserService service, UserDTOMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping()
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        var mappedDomainObj = mapper
                .toDomainObj(request);

        var savedDomainObj = service
                .create(mappedDomainObj);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(savedDomainObj));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id) {
        var mappedResponse = mapper
                .toResponse(service.getById(id));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mappedResponse);
    }

    @GetMapping
    public List<UserResponse> getAll() {
        var domainObjs = service
                .getAll()
                .spliterator();

        return StreamSupport.stream(domainObjs, false)
                .map(mapper::toResponse)
                .toList();
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponse> updateById(@PathVariable UUID id,
                                                   @RequestBody UserRequest request) {
        var mapperDomainObj = mapper
                .toDomainObj(request);

        var updatedDomainObj = service
                .updateById(id, mapperDomainObj);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResponse(updatedDomainObj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {
        service.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}