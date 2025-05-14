package engine.controller;

import engine.model.Quiz;
import engine.model.request.QuizRequest;
import engine.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuizController {
    private final QuizService quizService;
    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @PostMapping("/quizzes")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizRequest quizRequest){
        Quiz createdQuiz = quizService.createQuiz(quizRequest);

        return ResponseEntity.ok(createdQuiz);
    }

}
