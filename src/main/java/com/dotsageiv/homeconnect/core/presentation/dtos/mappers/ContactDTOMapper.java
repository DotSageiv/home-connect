package com.dotsageiv.homeconnect.core.presentation.dtos.mappers;

import com.dotsageiv.homeconnect.core.domain.entities.Contact;
import com.dotsageiv.homeconnect.core.presentation.dtos.requests.ContactRequest;
import com.dotsageiv.homeconnect.core.presentation.dtos.responses.ContactResponse;

public class ContactDTOMapper {
    public Contact toDomainObj(ContactRequest request) {
        return Contact.builder()
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .build();
    }

    public ContactRequest ToRequest(Contact domainObj) {
        return ContactRequest.builder()
                .email(domainObj.email())
                .phoneNumber(domainObj.phoneNumber())
                .build();
    }

    public ContactResponse toResponse(Contact domainObj) {
        return ContactResponse.builder()
                .email(domainObj.email())
                .phoneNumber(domainObj.phoneNumber())
                .build();
    }
}