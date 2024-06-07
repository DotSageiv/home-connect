package com.dotsageiv.homeconnect.infrastructure.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Table(name = "ADDRESSES")
@Entity
public class AddressEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 75, nullable = false)
    @NotBlank(message = "A cidade é obrigatório.")
    private String city;

    @Column(length = 75, nullable = false)
    @NotBlank(message = "O estado é obrigatório.")
    private String state;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    public AddressEntity() {}

    public AddressEntity(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}