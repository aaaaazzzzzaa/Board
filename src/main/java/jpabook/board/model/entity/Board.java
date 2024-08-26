package jpabook.board.model.entity;

import jakarta.persistence.*;
import jpabook.board.model.DeleteStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE board SET DELETE_STATUS = 'DELETE' WHERE board_no =?")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    private String title;

    @Column(length = 1000)
    private String body;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @SQLRestriction("DELETE_STATUS= 'ACTIVE'")
    private List<Comment> comments = new ArrayList<>();

    public Board addComment(String commentBody) {
        Comment comment = new Comment();
        comment.setBody(commentBody);
        comment.setDeleteStatus(DeleteStatus.ACTIVE);
        comment.setBoard(this);
        comments.add(comment);
        return this;
    }
}
