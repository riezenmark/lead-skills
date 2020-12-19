package org.omaewa.notastepik.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.omaewa.notastepik.util.Views;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
@NamedEntityGraph(name = "userWithRoles",
        attributeNodes = {
            @NamedAttributeNode("authorities")
        })
public class User implements UserDetails, PrimaryEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;
    @NotNull(message = "Имя не может быть пустым.")
    @Length(max = 31, message = "Слишком длинное имя (больше 31 символа).")
    @JsonView(Views.Representation.class)
    private String name;
    @NotNull(message = "Фамилия не может быть пустой.")
    @Length(max = 31, message = "Слишком длинная фамилия (больше 31 символа).")
    @JsonView(Views.Representation.class)
    private String surname;
    @Column(unique = true)
    @NotNull(message = "Логин не может быть пустым.")
    @Length(max = 31, message = "Слишком длинный логин (больше 31 символа).")
    @JsonView({Views.Representation.class, Views.Review.class})
    private String username;
    @NotNull(message = "Пароль не может быть пустым.")
    @Length(min = 8, max = 31, message = "Длина пароля должна быть между 8 и 31 символом.")
    private String password;
    @Column(unique = true)
    @NotNull(message = "Email не может быть пустым.")
    private String email;
    @JsonView(Views.Representation.class)
    private boolean enabled;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @JsonView(Views.Representation.class)
    private Set<Role> authorities;
    @JsonView(Views.IdRepresentation.class)
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }
}
