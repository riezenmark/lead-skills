package org.omaewa.notastepik.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.omaewa.notastepik.util.Views;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "announcement")
public class Announcement implements PrimaryEntity<Long>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;
    @NotNull
    @Column(unique = true)
    @JsonView({Views.Representation.class, Views.Review.class})
    private String heading;
    @NotNull
    @Column(columnDefinition = "TEXT")
    @JsonView(Views.Representation.class)
    private String description;
    @Min(value = 0)
    @Max(value = 5)
    @JsonView(Views.Representation.class)
    private float rating;
    @JsonView(Views.Representation.class)
    @Column(columnDefinition = "smallint default 0")
    private short numberOfParticipants;
    @Column(name = "date_time_from")
    @JsonView(Views.Representation.class)
    private Long timeFrom;
    @Column(name = "date_time_to")
    @JsonView(Views.Representation.class)
    private Long timeTo;
    @JsonView(Views.Representation.class)
    @Column(columnDefinition = "boolean default false")
    private boolean occupied;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @JsonView(Views.IdRepresentation.class)
    private User author;
    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonView(Views.Representation.class)
    private AnnouncementType type;
}
