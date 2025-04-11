package br.com.four.ecom.core.infrastructure.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "role_name", nullable = false)
    private String roleName;
}
