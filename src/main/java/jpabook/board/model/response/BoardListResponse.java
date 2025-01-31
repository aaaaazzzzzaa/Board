package jpabook.board.model.response;

import jpabook.board.model.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardListResponse {

    private Long boardNo;
    private String title;

    public static BoardListResponse from(Board board) {
        return new BoardListResponse(
                board.getBoardNo(),
                board.getTitle()
        );
    }
}
