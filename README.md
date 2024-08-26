# 게시판 구현하기 (BoardApp)

---

## 요구사항

1. 게시글 등록
   1. 단 건의 게시글을 등록할 수 있어야 된다.
2. 게시글 삭제
   1. 단 건의 게시글을 삭제할 수 있어야 한다.
      1. 게시글의 삭제 방법은 soft delete를 사용
      2. 게시글 삭제시 댓글도 삭제 되어야 함
3. 게시글 수정
    1. 단 건의 게시글을 수정할 수 있어야 한다.
       1. 삭제된 게시글은 수정할 수 없다.
4. 게시글 목록 조회
   1. 등록된 게시글의 목록을 조회한다.
      1. 응답에는 본문이 포함되지 않는다.
   2. 페이징이 가능해야 한다.
      1. 정렬 순서는, 최신 글이 우선순위가 가장 높다.
5. 댓글 등록
   1. 게시글에 댓글을 등록한다.
6. 댓글 수정
   1. 게시글에 댓글을 수정한다.
7. 댓글 삭제
   1. 게시글에 댓글을 삭제한다.
   2. 삭제는 soft delete로 진행
8. 게시글 단 건 조회
   1. 게시글의 단 건을 조회한다.
      1. 게시글의 제목과 본문 모두 응답에 포함되어ㅑㅇ 한다.
      2. 해당 게시 글에 등록된 댓글 리스트도 같이 응답에 포함되어야 한다.
         1. 댓글 중 삭제된 댓글은 포함하지 않는다.
         2. 댓글 리스트도 페이징이 필요하나, 여기에선 무시한다.

---

## API
```
    POST    /board # 생성
    GET     /boards?page={no}&pageSize={no} # 전체 조회(페이징)
    GET     /boardNo?{no} # 특정 게시글 조회
    DELETE  /board # 삭제
    POST    /comment # 댓글 생성
    
```
