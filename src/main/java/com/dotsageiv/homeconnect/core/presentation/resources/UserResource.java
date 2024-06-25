package com.dotsageiv.homeconnect.core.presentation.resources;

import com.dotsageiv.homeconnect.core.domain.interfaces.UserService;
import com.dotsageiv.homeconnect.core.presentation.dtos.mappers.UserDTOMapper;
import com.dotsageiv.homeconnect.core.presentation.dtos.requests.UserRequest;
import com.dotsageiv.homeconnect.core.presentation.dtos.responses.UserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserResource {
    private final UserService service;
    private final UserDTOMapper mapper;

    @PostMapping("/")
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
        var mappedDomainObj = mapper
                .toDomainObj(request);

        var savedDomainObj = service
                .create(mappedDomainObj);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(savedDomainObj));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long userId) {
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
    public ResponseEntity<UserResponse> updateById(@Valid
                                                   @PathVariable Long userId,
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
    public ResponseEntity<?> deleteById(@PathVariable Long userId) {
        service.deleteById(userId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}