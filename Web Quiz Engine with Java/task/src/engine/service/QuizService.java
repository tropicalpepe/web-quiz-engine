package engine.service;

import engine.model.Quiz;
import engine.model.request.QuizRequest;
import engine.repository.QuizRepository;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    public QuizService(QuizRepository quizRepository){
        this.quizRepository = quizRepository;
    }

    public Quiz createQuiz(QuizRequest quizRequest){
        Quiz quiz = new Quiz();
        quiz.setTitle(quizRequest.getTitle());
        quiz.setText(quizRequest.getText());
        quiz.setOptions(quizRequest.getOptions());
        quiz.setAnswer(quizRequest.getAnswer());
        return quizRepository.save(quiz);
    }
}
