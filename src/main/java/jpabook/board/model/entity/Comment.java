package jpabook.board.model.entity;

import jakarta.persistence.*;
import jpabook.board.model.DeleteStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE Comment SET DELETE_STATUS = 'DELETE' WHERE comment_no =?")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNo;

    @Column(length = 1000)
    private String body;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    // n + 1 방지
    @ManyToOne(fetch = FetchType.LAZY) // n개의 쿼리가 발생하는 문제는 join fetch 라는 쿼리를 통해 해결 가능
    @JoinColumn(name = "boardNo")
    private Board board;
}
