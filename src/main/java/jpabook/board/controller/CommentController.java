package jpabook.board.controller;

import jpabook.board.model.request.CommentPostRequest;
import jpabook.board.model.response.BoardResponse;
import jpabook.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("comment")
    public BoardResponse writeComment(
            @RequestBody CommentPostRequest commentPostRequest
    ) {
        return commentService.writeComment(commentPostRequest);
    }
}
