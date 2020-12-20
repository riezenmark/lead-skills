package org.omaewa.notastepik.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.omaewa.notastepik.util.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "organization")
public class Organization implements PrimaryEntity<Long>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;
    @NotNull
    @Length(max = 31, message = "Слишком длинное название (больше 31 символа).")
    @Column(unique = true)
    @JsonView(Views.Representation.class)
    private String name;
    @NotNull
    @Length(min = 8, max = 31, message = "Длина пароля должна быть между 8 и 31 символом.")
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Announcement> announcements;
}
