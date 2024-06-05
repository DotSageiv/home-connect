package com.dotsageiv.HomeConnect.infrastructure.persistence.notifications;

public class EntityNotFoundNotification extends RuntimeException {
    public EntityNotFoundNotification(String message) {
        super(message);
    }
}