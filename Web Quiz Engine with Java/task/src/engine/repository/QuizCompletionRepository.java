package engine.repository;

import engine.model.Quiz;
import engine.model.QuizCompletion;
import engine.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface QuizCompletionRepository extends JpaRepository<QuizCompletion, Long> {
    Page<QuizCompletion> getQuizCompletionsBySolvedBy(User solvedBy, Pageable pageable);

    @Modifying
    void deleteBySolvedQuiz(Quiz solvedQuiz);
}
