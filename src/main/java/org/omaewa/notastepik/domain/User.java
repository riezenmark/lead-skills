package org.omaewa.notastepik.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.omaewa.notastepik.util.Views;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
@NamedEntityGraph(name = "userWithRoles",
        attributeNodes = {
            @NamedAttributeNode("authorities")
        })
public class User implements PrimaryEntity<String>, Serializable {
    @Id
    @JsonView(Views.Id.class)
    private String id;
    private String userpic;
    private String gender;
    private String locale;
    private LocalDateTime lastVisit;
    @NotNull(message = "Логин не может быть пустым.")
    @Length(max = 31, message = "Слишком длинный логин (больше 31 символа).")
    @JsonView({Views.Representation.class, Views.Review.class})
    private String username;
    private String email;
    @JsonView(Views.Representation.class)
    private boolean enabled;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @JsonView(Views.Representation.class)
    private Set<Role> authorities;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Organization organization;
}
