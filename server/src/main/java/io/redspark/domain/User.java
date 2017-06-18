package io.redspark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(name = "UK_LOGIN", columnNames = "user_login"))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @GeneratedValue
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_login", nullable = false, updatable = false)
    private String login;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_admin")
    private Boolean admin;

    @OneToOne(mappedBy = "user")
    private Person person;
}
