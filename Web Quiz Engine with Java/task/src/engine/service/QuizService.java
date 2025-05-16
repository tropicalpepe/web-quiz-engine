package engine.service;

import engine.model.Quiz;
import engine.model.request.QuizRequest;
import engine.model.request.SolutionRequest;
import engine.model.response.QuizSolutionResponse;
import engine.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        Optional<Quiz> quizOptional = quizRepository.findById(id);

        if (quizOptional.isEmpty())
            throw new QuizNotFoundException();

        return quizOptional.get();
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public QuizSolutionResponse checkSolution(int id, SolutionRequest solutionRequest){
        Quiz correctQuiz = getQuiz(id);

        List<Integer> solution = solutionRequest.getAnswer()
                .stream()
                .sorted()
                .toList();

        List<Integer> correct = correctQuiz.getAnswer()
                .stream()
                .sorted()
                .toList();

        QuizSolutionResponse solutionResponse = new QuizSolutionResponse();
        if (solution.equals(correct)) {
            solutionResponse.setSuccess(true);
            solutionResponse.setFeedback("Congratulations, you're right!");
            return solutionResponse;
        }

        solutionResponse.setSuccess(false);
        solutionResponse.setFeedback("Wrong answer! Please, try again.");
        return solutionResponse;
    }
}
