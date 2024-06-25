package com.dotsageiv.homeconnect.infrastructure.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Table(name = "CONTACTS")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ContactEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "O email é inválido.")
    @Column(unique = true, nullable = false)
    @NotBlank(message = "O email é obrigatório.")
    private String email;

    @Size(min = 11, max = 13,
            message = "O número de telefone deve possui entre 11 ou 13 caracteres.")
    @Column(nullable = false, length = 13)
    @NotBlank(message = "O número de telefone é obrigatório.")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}