package engine.service;

import engine.model.Quiz;
import engine.model.dto.QuizAnswerResponse;
import engine.repository.QuizRepository;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    public QuizService(QuizRepository quizRepository){
        this.quizRepository = quizRepository;
    }

    public Quiz getQuiz(){
        return quizRepository.getEvent();
    }

    public QuizAnswerResponse checkAnswer(int answerIndex){
        QuizAnswerResponse quizAnswerResponse = new QuizAnswerResponse();
        if (answerIndex == 2) {
            quizAnswerResponse.setSuccess(true);
            quizAnswerResponse.setFeedback("Congratulations, you're right!");
            return quizAnswerResponse;
        }
        quizAnswerResponse.setSuccess(false);
        quizAnswerResponse.setFeedback("Wrong answer! Please, try again.");
        return quizAnswerResponse;
    }
}
