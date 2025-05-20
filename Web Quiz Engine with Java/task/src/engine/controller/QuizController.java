package engine.controller;

import engine.model.Quiz;
import engine.model.request.QuizRequest;
import engine.model.request.SolutionRequest;
import engine.model.response.QuizCompletionResponse;
import engine.model.response.QuizSolutionResponse;
import engine.service.QuizCompletionService;
import engine.service.QuizNotFoundException;
import engine.service.QuizService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class QuizController {
    private final QuizService quizService;
    private final QuizCompletionService quizCompletionService;

    @PostMapping("/quizzes")
    public ResponseEntity<Quiz> createQuiz(
            @Valid @RequestBody QuizRequest quizRequest,
            @AuthenticationPrincipal UserDetails userDetails){
        Quiz createdQuiz = quizService.createQuiz(quizRequest, userDetails);

        return ResponseEntity.ok(createdQuiz);
    }

    @GetMapping("/quizzes/completed")
    public Page<QuizCompletionResponse> getCompletedQuiz(
            @RequestParam int page,
            @AuthenticationPrincipal UserDetails userDetails) {
        return quizCompletionService.getCompletedQuizzes(page, userDetails);
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable(name = "id") int id) {
        Quiz quiz = quizService.getQuiz(id);

        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/quizzes")
    public ResponseEntity<Page<Quiz>> getQuizzes(
            @RequestParam int page){

        Page<Quiz> quizzes = quizService.getQuizzes(page);

        return ResponseEntity.ok(quizzes);
    }

    @PostMapping("/quizzes/{id}/solve")
    public ResponseEntity<QuizSolutionResponse> solveQuiz(
            @PathVariable(name = "id") int id,
            @RequestBody SolutionRequest solutionRequest,
            @AuthenticationPrincipal UserDetails userDetails) {
        QuizSolutionResponse solutionResponse = quizService.checkSolution(id, solutionRequest, userDetails);

        return ResponseEntity.ok(solutionResponse);
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<Void> deleteQuiz(
            @PathVariable(name = "id") int id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        quizService.deleteQuiz(id, userDetails);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
