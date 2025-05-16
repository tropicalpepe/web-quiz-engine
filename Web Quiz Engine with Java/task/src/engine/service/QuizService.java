package engine.service;

import engine.model.Quiz;
import engine.model.request.QuizRequest;
import engine.model.request.SolutionRequest;
import engine.model.response.QuizSolutionResponse;
import engine.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

    public Quiz getQuiz(int id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public QuizSolutionResponse checkSolution(int id, SolutionRequest solutionRequest){
        Quiz quizToCheck = getQuiz(id);

        int[] solution = Arrays.stream(solutionRequest.getAnswer()).sorted().toArray();
        int[] correct = Arrays.stream(quizToCheck.getAnswer()).sorted().toArray();

        QuizSolutionResponse solutionResponse = new QuizSolutionResponse();
        if (Arrays.equals(solution, correct)) {
            solutionResponse.setSuccess(true);
            solutionResponse.setFeedback("Congratulations, you're right!");
            return solutionResponse;
        }

        solutionResponse.setSuccess(false);
        solutionResponse.setFeedback("Wrong answer! Please, try again.");
        return solutionResponse;
    }
}
