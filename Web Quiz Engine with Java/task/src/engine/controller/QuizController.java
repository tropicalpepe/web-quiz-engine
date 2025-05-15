package engine.controller;

import engine.model.Quiz;
import engine.model.request.QuizRequest;
import engine.model.response.QuizSolutionResponse;
import engine.service.QuizService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable(name = "id") int id) {
        Quiz quiz = quizService.getQuiz(id);

        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/quizzes")
    public ResponseEntity<List<Quiz>> getQuizzes(){
        List<Quiz> quizzes = quizService.getAllQuizzes();

        return ResponseEntity.ok(quizzes);
    }

    @PostMapping("/quizzes/{id}/solve")
    public ResponseEntity<QuizSolutionResponse> checkSolution(
            @PathVariable(name = "id") int id,
            @RequestParam(name = "answer") int answer) {
        QuizSolutionResponse solutionResponse = quizService.checkSolution(id, answer);

        return ResponseEntity.ok(solutionResponse);
    }

}
