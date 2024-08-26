package jpabook.board.service;

import jpabook.board.model.entity.Board;
import jpabook.board.model.request.CommentPostRequest;
import jpabook.board.model.response.BoardResponse;
import jpabook.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;

    public BoardResponse writeComment(CommentPostRequest commentPostRequest) {
        Optional<Board> boardOptional = boardRepository.findBoardWithCommentsByBoardNo(commentPostRequest.getBoardNo());
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 게시물 입니다."));

        board.addComment(commentPostRequest.getCommentBody());
        Board res = boardRepository.save(board);

        return BoardResponse.from(res);
    }
}
