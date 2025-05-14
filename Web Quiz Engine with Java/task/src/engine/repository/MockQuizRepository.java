package engine.repository;

import engine.model.Quiz;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MockQuizRepository implements QuizRepository{
    private final Map<Long, Quiz> inMemoryDatabase = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Quiz save(Quiz quiz) {
        long recordId = idCounter.getAndIncrement();
        quiz.setId((int) recordId);
        inMemoryDatabase.put(recordId, quiz);

        return quiz;
    }
}
