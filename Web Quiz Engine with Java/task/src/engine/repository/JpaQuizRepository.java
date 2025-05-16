package engine.repository;

import engine.model.Quiz;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

@Primary
public interface JpaQuizRepository extends JpaRepository<Quiz, Long>, QuizRepository {}
