package ar.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "db_todos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 500, nullable = false)
    private String description;
    @Temporal(TemporalType.DATE)
    private Date targetDate;
    @Column(columnDefinition = "boolean default false")
    private boolean isDone;
}
