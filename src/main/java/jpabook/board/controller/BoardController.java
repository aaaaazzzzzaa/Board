package jpabook.board.controller;

import jpabook.board.model.request.BoardDeleteReqeust;
import jpabook.board.model.request.BoardPostRequest;
import jpabook.board.model.response.BoardListResponse;
import jpabook.board.model.response.BoardResponse;
import jpabook.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("board")
    public BoardResponse writeBoard(
            @RequestBody BoardPostRequest boardPostRequest
    ) {
        return boardService.writeBoard(boardPostRequest);
    }

    @GetMapping("boards")
    public List<BoardListResponse> searchBoardList(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize
    ) {
        return boardService.searchBoardList(page, pageSize);
    }

    @GetMapping
    public BoardResponse searchBoard(
            @RequestParam("boardNo") Long boardNo
    ) {
        return boardService.searchBoard(boardNo);
    }

    @DeleteMapping("board")
    public String deleteBoard(
            @RequestBody BoardDeleteReqeust boardDeleteReqeust
    ) {
        return boardService.deleteBoard(boardDeleteReqeust);
    }
}
