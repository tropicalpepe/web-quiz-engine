package engine.repository;

import engine.model.Quiz;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Primary
public interface QuizRepository extends JpaRepository<Quiz, Long> {}
