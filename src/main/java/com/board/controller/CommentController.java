package com.board.controller;

import com.board.dto.CommentForm;
import com.board.entity.Answer;
import com.board.entity.Comment;
import com.board.entity.Question;
import com.board.entity.User;
import com.board.service.AnswerService;
import com.board.service.CommentService;
import com.board.service.QuestionService;
import com.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api/question/{questionId}/answer/{answerId}/comments")
@RequiredArgsConstructor
@Controller
public class CommentController {
    private final CommentService commentService;

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public @ResponseBody ResponseEntity<?> createComment(@PathVariable("questionId") Integer questionId,
                                                         @PathVariable("answerId") Integer answerId,
                                                         @Valid @RequestBody CommentForm commentForm, BindingResult bindingResult, Principal principal) {
        System.out.println(commentForm.content());
        Question question = this.questionService.getQuestion(questionId);
        Answer answer = this.answerService.getAnswer(answerId);
        User user = this.userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Comment comment = this.commentService.create(question,
                user, answer, commentForm.content());
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }
}
