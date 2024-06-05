package com.dotsageiv.HomeConnect.core.presentation.resources;

import com.dotsageiv.HomeConnect.core.presentation.dtos.mappers.ContactDTOMapper;
import com.dotsageiv.HomeConnect.core.presentation.dtos.requests.ContactRequest;
import com.dotsageiv.HomeConnect.core.presentation.dtos.responses.ContactResponse;
import com.dotsageiv.HomeConnect.infrastructure.gateway.services.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/contacts")
public class ContactResource {
    private final ContactService service;
    private final ContactDTOMapper mapper;

    public ContactResource(ContactService service, ContactDTOMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping()
    public ResponseEntity<ContactResponse> create(@RequestBody ContactRequest request) {
        var mappedDomainObj = mapper
                .toDomainObj(request);

        var savedDomainObj = service
                .create(mappedDomainObj);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(savedDomainObj));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponse> getById(@PathVariable UUID id) {
        var mappedResponse = mapper
                .toResponse(service.getById(id));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mappedResponse);
    }

    @GetMapping
    public List<ContactResponse> getAll() {
        var domainObjs = service
                .getAll()
                .spliterator();

        return StreamSupport.stream(domainObjs, false)
                .map(mapper::toResponse)
                .toList();
    }

    @PutMapping("{id}")
    public ResponseEntity<ContactResponse> updateById(@PathVariable UUID id,
                                                      @RequestBody ContactRequest request) {
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