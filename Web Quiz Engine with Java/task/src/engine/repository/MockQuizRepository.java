package engine.repository;

import engine.model.Quiz;
import org.springframework.stereotype.Repository;

@Repository
public class MockQuizRepository implements QuizRepository{

    @Override
    public Quiz getEvent() {
        Quiz quiz = new Quiz();

        quiz.setTitle("The Java Logo");
        quiz.setText("What is depicted on the Java logo?");
        quiz.setOptions(new String[] {"Robot","Tea leaf","Cup of coffee","Bug"});

        return quiz;
    }
}
