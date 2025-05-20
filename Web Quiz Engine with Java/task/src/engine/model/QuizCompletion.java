package engine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class QuizCompletion {
    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime completedAt;
    @ManyToOne
    private Quiz solvedQuiz;
    @ManyToOne
    private User solvedBy;
}
