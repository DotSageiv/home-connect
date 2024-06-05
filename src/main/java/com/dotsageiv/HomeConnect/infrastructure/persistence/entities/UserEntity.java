package com.dotsageiv.HomeConnect.infrastructure.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Table(name = "USERS")
@Entity
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 14, max = 14, message = "O CPF deve conter exatamente 14 números.")
    @Column(unique = true, nullable = false, length = 14)
    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;

    @Size(max = 75, message = "O nome de completo possui 75 caracteres ao máximo.")
    @Column(nullable = false, length = 75)
    @NotBlank(message = "O nome completo é obrigatório.")
    private String fullName;

    @Size(max = 75, message = "O nome de usuário possui 75 caracteres ao máximo")
    @Column(unique = true, nullable = false, length = 75)
    @NotBlank(message = "O nome de usuário é obrigatório.")
    private String username;

    @NotBlank(message = "A senha é obrigatório.")
    @Column(nullable = false, length = 10)
    @Size(max = 10, message = "A senha deve conter pelo menos 10 caracteres")
    private String password;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Set<ContactEntity> contacts = new HashSet<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Set<AddressEntity> addresses = new HashSet<>();

    public UserEntity() {}

    public UserEntity(String cpf, String fullName,
                      String username, String password) {
        this.cpf = cpf;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ContactEntity> getContacts() {
        return contacts;
    }

    public Set<AddressEntity> getAddresses() {
        return addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}