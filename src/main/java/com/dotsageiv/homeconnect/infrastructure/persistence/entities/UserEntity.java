package com.dotsageiv.homeconnect.infrastructure.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "USERS")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
}