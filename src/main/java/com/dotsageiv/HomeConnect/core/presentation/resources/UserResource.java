package com.dotsageiv.HomeConnect.core.presentation.resources;

import com.dotsageiv.HomeConnect.core.domain.interfaces.UserService;
import com.dotsageiv.HomeConnect.core.presentation.dtos.mappers.UserDTOMapper;
import com.dotsageiv.HomeConnect.core.presentation.dtos.requests.UserRequest;
import com.dotsageiv.HomeConnect.core.presentation.dtos.responses.UserResponse;
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

    @PostMapping("/")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        var mappedDomainObj = mapper
                .toDomainObj(request);

        var savedDomainObj = service
                .create(mappedDomainObj);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(savedDomainObj));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID userId) {
        var mappedResponse = mapper
                .toResponse(service.getById(userId));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mappedResponse);
    }

    @GetMapping("/")
    public List<UserResponse> getAll() {
        var domainObjs = service
                .getAll()
                .spliterator();

        return StreamSupport.stream(domainObjs, false)
                .map(mapper::toResponse)
                .toList();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateById(@PathVariable UUID userId,
                                                   @RequestBody UserRequest request) {
        var mapperDomainObj = mapper
                .toDomainObj(request);

        var updatedDomainObj = service
                .updateById(userId, mapperDomainObj);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResponse(updatedDomainObj));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteById(@PathVariable UUID userId) {
        service.deleteById(userId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}