package org.omaewa.notastepik.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.omaewa.notastepik.util.Views;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "module")
public class Module implements PrimaryEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;
    @NotNull
    @Min(value = 1)
    @JsonView({Views.Representation.class, Views.Lesson.class})
    private short number;
    @NotNull
    @JsonView({Views.Representation.class, Views.Lesson.class})
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id")
    @JsonView({Views.IdRepresentation.class, Views.Lesson.class})
    private Announcement announcement;
}
