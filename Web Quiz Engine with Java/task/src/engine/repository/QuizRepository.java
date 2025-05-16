package engine.repository;

import engine.model.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizRepository {
    public Quiz save(Quiz quiz);
    public Optional<Quiz> findById(long id);
    public List<Quiz> findAll();
}
