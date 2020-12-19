package org.omaewa.notastepik.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.omaewa.notastepik.util.Views;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
@Getter
@Setter
@Table(name = "announcement")
public class Announcement implements PrimaryEntity<Long> {
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
    @Max(value = 10)
    @JsonView(Views.Representation.class)
    private short rating;
    @JsonView(Views.Representation.class)
    @Column(columnDefinition = "smallint default 0")
    private short numberOfParticipants;
    @Column(name = "date_time_from", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonView(Views.Representation.class)
    private Calendar timestampFrom;
    @Column(name = "date_time_to", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonView(Views.Representation.class)
    private Calendar timestampTo;
    @JsonView(Views.Representation.class)
    @Column(columnDefinition = "boolean default false")
    private boolean occupied;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonView(Views.IdRepresentation.class)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    @JsonView(Views.Representation.class)
    private Subject subject;
    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonView(Views.Representation.class)
    private AnnouncementType type;
}
