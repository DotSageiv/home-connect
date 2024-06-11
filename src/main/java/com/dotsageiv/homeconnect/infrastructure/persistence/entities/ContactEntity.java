package com.dotsageiv.homeconnect.infrastructure.persistence.entities;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "CONTACTS")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ContactEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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