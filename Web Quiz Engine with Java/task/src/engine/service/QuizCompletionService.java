package engine.service;

import engine.model.Quiz;
import engine.model.QuizCompletion;
import engine.model.User;
import engine.model.response.QuizCompletionResponse;
import engine.repository.QuizCompletionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class QuizCompletionService {
    private final int PAGE_SIZE = 10;
    private final UserService userService;
    private final QuizCompletionRepository quizCompletionRepository;

    public void createQuizCompletionNow(Quiz solvedQuiz, User solvedBy){
        QuizCompletion quizCompletion = new QuizCompletion();
        quizCompletion.setCompletedAt(LocalDateTime.now());
        quizCompletion.setSolvedQuiz(solvedQuiz);
        quizCompletion.setSolvedBy(solvedBy);

        quizCompletionRepository.save(quizCompletion);
    }

    public Page<QuizCompletionResponse> getCompletedQuizzes(int page, UserDetails userDetails) {
        User user = userService.getUser(userDetails);

        Pageable pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "completedAt"));

        Page<QuizCompletion> pagedQuizCompletions = quizCompletionRepository.getQuizCompletionsBySolvedBy(user, pageRequest);

        return pagedQuizCompletions.map(QuizCompletionResponse::from);
    }
}
