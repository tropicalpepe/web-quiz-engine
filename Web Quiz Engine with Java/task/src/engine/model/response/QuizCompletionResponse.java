package engine.model.response;

import engine.model.QuizCompletion;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuizCompletionResponse {
    private long id;
    private LocalDateTime completedAt;

    public static QuizCompletionResponse from(QuizCompletion quizCompletion){
        QuizCompletionResponse quizCompletionResponse = new QuizCompletionResponse();

        quizCompletionResponse.setId(quizCompletion.getSolvedQuiz().getId());
        quizCompletionResponse.setCompletedAt(quizCompletion.getCompletedAt());

        return quizCompletionResponse;
    }
}
