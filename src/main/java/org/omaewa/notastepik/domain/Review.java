package org.omaewa.notastepik.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.omaewa.notastepik.util.Views;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "review")
public class Review implements PrimaryEntity<Long>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;
    @Min(value = 1)
    @Max(value = 5)
    @JsonView(Views.Review.class)
    private short evaluation;
    @JsonView(Views.Review.class)
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonView(Views.Review.class)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id")
    @JsonView(Views.Review.class)
    private Announcement announcement;
}
