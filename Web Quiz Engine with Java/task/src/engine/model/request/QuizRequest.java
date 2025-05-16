package engine.model.request;

import engine.model.Quiz;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QuizRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @NotNull
    @Size(min = 2)
    private List<String> options;

    private List<Integer> answer = new ArrayList<>();

    public Quiz toQuiz(){
        Quiz quiz = new Quiz();

        quiz.setTitle(getTitle());
        quiz.setText(getText());
        quiz.setOptions(getOptions());
        quiz.setAnswer(getAnswer());
        return quiz;
    }
}
