package com.dotsageiv.homeconnect.core.presentation.resources;

import com.dotsageiv.homeconnect.core.domain.interfaces.ContactService;
import com.dotsageiv.homeconnect.core.presentation.dtos.mappers.ContactDTOMapper;
import com.dotsageiv.homeconnect.core.presentation.dtos.requests.ContactRequest;
import com.dotsageiv.homeconnect.core.presentation.dtos.responses.ContactResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/contacts")
@AllArgsConstructor
public class ContactResource {
    private final ContactService service;
    private final ContactDTOMapper mapper;

    @PostMapping("/{userId}")
    public ResponseEntity<ContactResponse> create(@Valid
                                                  @PathVariable UUID userId,
                                                  @RequestBody ContactRequest request) {
        var mappedDomainObj = mapper
                .toDomainObj(request);

        var savedDomainObj = service
                .create(userId, mappedDomainObj);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(savedDomainObj));
    }

    @GetMapping("/{contactId}/{userId}")
    public ResponseEntity<ContactResponse> getById(@PathVariable UUID contactId,
                                                   @PathVariable UUID userId) {
        var mappedResponse = mapper
                .toResponse(service.getById(contactId, userId));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mappedResponse);
    }

    @GetMapping("/{userId}")
    public List<ContactResponse> getAll(@PathVariable UUID userId) {
        var domainObjs = service
                .getAll(userId)
                .spliterator();

        return StreamSupport.stream(domainObjs, false)
                .map(mapper::toResponse)
                .toList();
    }

    @PutMapping("/{contactId}/{userId}")
    public ResponseEntity<ContactResponse> updateById(@Valid
                                                      @PathVariable UUID contactId,
                                                      @PathVariable UUID userId,
                                                      @RequestBody ContactRequest request) {
        var mapperDomainObj = mapper
                .toDomainObj(request);

        var updatedDomainObj = service
                .updateById(contactId, userId, mapperDomainObj);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResponse(updatedDomainObj));
    }

    @DeleteMapping("/{contactId}/{userId}")
    public ResponseEntity<?> deleteById(@PathVariable UUID contactId,
                                        @PathVariable UUID userId) {
        service.deleteById(contactId, userId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}