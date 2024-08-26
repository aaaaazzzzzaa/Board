package jpabook.board.service;

import jpabook.board.model.DeleteStatus;
import jpabook.board.model.entity.Board;
import jpabook.board.model.request.BoardDeleteReqeust;
import jpabook.board.model.request.BoardPostRequest;
import jpabook.board.model.response.BoardListResponse;
import jpabook.board.model.response.BoardResponse;
import jpabook.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponse writeBoard(BoardPostRequest request) {
        Board board = new Board();
        board.setTitle(request.getTitle());
        board.setBody(request.getBody());
        board.setDeleteStatus(DeleteStatus.ACTIVE);
        return BoardResponse.from(boardRepository.save(board));
    }

    public List<BoardListResponse> searchBoardList(int page, int pageSize) {
        return boardRepository.findAllByDeleteStatus(
                DeleteStatus.ACTIVE,
                PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "boardNo"))
        ).map(BoardListResponse::from).toList();
    }

    public BoardResponse searchBoard(Long boardNo) {
        return boardRepository.findBoardWithCommentsByBoardNo(boardNo)
                              .map(BoardResponse::from).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
    }

    public String deleteBoard(BoardDeleteReqeust boardDeleteReqeust) {

        Optional<Board> boardOptional = boardRepository.findById(boardDeleteReqeust.getBoardNo());
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 게시물 입니다."));

        boardRepository.delete(board);

        return "OK";
    }
}
