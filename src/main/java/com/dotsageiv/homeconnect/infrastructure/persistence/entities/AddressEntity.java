package com.dotsageiv.homeconnect.infrastructure.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Table(name = "ADDRESSES")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AddressEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    @NotBlank(message = "A cidade é obrigatório.")
    private String city;

    @Column(length = 75, nullable = false)
    @NotBlank(message = "O estado é obrigatório.")
    private String state;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;
}