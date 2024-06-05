package com.dotsageiv.HomeConnect.core.presentation.dtos.mappers;

import com.dotsageiv.HomeConnect.core.domain.entities.Contact;
import com.dotsageiv.HomeConnect.core.presentation.dtos.requests.ContactRequest;
import com.dotsageiv.HomeConnect.core.presentation.dtos.responses.ContactResponse;

public class ContactDTOMapper {
    public Contact toDomainObj(ContactRequest request) {
        return new Contact(request.email(), request.phoneNumber());
    }

    public ContactRequest ToRequest(Contact domainObj) {
        return new ContactRequest(domainObj.email(), domainObj.phoneNumber());
    }

    public ContactResponse toResponse(Contact domainObj) {
        return new ContactResponse(domainObj.email(), domainObj.phoneNumber());
    }
}