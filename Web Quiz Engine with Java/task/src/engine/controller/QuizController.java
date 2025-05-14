package engine.controller;

import engine.model.Quiz;
import engine.model.dto.QuizAnswerResponse;
import engine.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    private final QuizService quizService;
    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<Quiz> getQuiz(){
        Quiz quiz = quizService.getQuiz();

        return ResponseEntity.ok(quiz);
    }

    @PostMapping
    public ResponseEntity<QuizAnswerResponse> checkQuiz(@RequestParam(name = "answer") int answerIndex){
        QuizAnswerResponse quizAnswerResponse = quizService.checkAnswer(answerIndex);

        return ResponseEntity.ok(quizAnswerResponse);
    }
}
