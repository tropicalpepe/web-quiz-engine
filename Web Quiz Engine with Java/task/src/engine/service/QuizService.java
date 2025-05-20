package engine.service;

import engine.exception.AccessDeniedException;
import engine.model.Quiz;
import engine.model.QuizCompletion;
import engine.model.User;
import engine.model.request.QuizRequest;
import engine.model.request.SolutionRequest;
import engine.model.response.QuizSolutionResponse;
import engine.repository.QuizCompletionRepository;
import engine.repository.QuizRepository;
import engine.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizService {
    private final int PAGE_SIZE = 10;
    private final QuizRepository quizRepository;
    private final UserService userService;
    private final QuizCompletionService quizCompletionService;
    private final QuizCompletionRepository quizCompletionRepository;

    public Quiz createQuiz(QuizRequest quizRequest, UserDetails userDetails){
        Quiz quiz = quizRequest.toQuiz();

        User createdBy = userService.getUser(userDetails);

        quiz.setCreatedBy(createdBy);

        return quizRepository.save(quiz);
    }

    public Quiz getQuiz(int id) {
        Optional<Quiz> quizOptional = quizRepository.findById((long) id);

        if (quizOptional.isEmpty())
            throw new QuizNotFoundException();

        return quizOptional.get();
    }

    public Page<Quiz> getQuizzes(int page) {
        Pageable requestedPage = PageRequest.of(page, PAGE_SIZE);

        return quizRepository.findAll(requestedPage);
    }

    public QuizSolutionResponse checkSolution(int id, SolutionRequest solutionRequest, UserDetails userDetails){
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
            User solvedBy = userService.getUser(userDetails);
            quizCompletionService.createQuizCompletionNow(correctQuiz, solvedBy);

            solutionResponse.setSuccess(true);
            solutionResponse.setFeedback("Congratulations, you're right!");
            return solutionResponse;
        }

        solutionResponse.setSuccess(false);
        solutionResponse.setFeedback("Wrong answer! Please, try again.");
        return solutionResponse;
    }

    @Transactional
    public void deleteQuiz(int id, UserDetails userDetails){
        Quiz quizToDelete = getQuiz(id);

        User whoRequestedDelete = userService.getUser(userDetails);

        if (!quizToDelete.getCreatedBy().equals(whoRequestedDelete))
            throw new AccessDeniedException();

        quizCompletionRepository.deleteBySolvedQuiz(quizToDelete);
        quizRepository.delete(quizToDelete);
    }
}
