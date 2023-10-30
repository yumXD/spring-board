package com.board.service;

import com.board.entity.Answer;
import com.board.entity.Comment;
import com.board.entity.Question;
import com.board.entity.User;
import com.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment create(Question question, User author, Answer answer, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAuthor(author);
        comment.setQuestion(question);
        comment.setAnswer(answer);
        this.commentRepository.save(comment);
        return comment;
    }
}
