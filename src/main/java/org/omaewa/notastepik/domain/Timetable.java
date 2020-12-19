package org.omaewa.notastepik.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.omaewa.notastepik.util.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "timetable")
public class Timetable implements PrimaryEntity<Long>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;
    @NotNull
    @OneToOne
    @JsonView(Views.IdRepresentation.class)
    private User user;
    @OneToMany
    @JsonView(Views.IdRepresentation.class)
    private List<Announcement> announcements;
}
