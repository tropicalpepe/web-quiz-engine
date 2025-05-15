package engine.repository;

import engine.model.Quiz;

import java.util.List;

public interface QuizRepository {
    public Quiz save(Quiz quiz);
    public Quiz findById(long id);
    public List<Quiz> findAll();
}
