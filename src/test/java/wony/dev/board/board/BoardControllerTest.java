package wony.dev.board.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import wony.dev.board.board.model.BoardDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BoardControllerTest {

    @Autowired
    private BoardController boardController;
    @Test
    @DisplayName("게시글 등록")
    void add(){
        // given
        BoardDto boardDto = BoardDto.builder()
                .title("제목 5")
                .content("내용 5")
                .build();

        //when
        ResponseEntity<BoardDto> response = boardController.add(boardDto);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        BoardDto responseBoardDto = response.getBody();
        assertThat(responseBoardDto.getTitle()).isEqualTo(boardDto.getTitle());
        assertThat(responseBoardDto.getContent()).isEqualTo(boardDto.getContent());
    }

    @Test
    @DisplayName("게시글 전부 가져오기")
    void getBoards(){
        // given

        // when
        ResponseEntity<List<BoardDto>> response = boardController.getBoards();

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<BoardDto> boardDtos = response.getBody();
        for (BoardDto boardDto : boardDtos) {
            System.out.println("boardDto = " + boardDto);
        }
    }

    @Test
    @DisplayName("게시글 ID로 가져오기")
    void getBoardById(){
        // given
        Long id = 101l;

        // when
        ResponseEntity<BoardDto> response = boardController.getBoardById(id);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        BoardDto responseBoard = response.getBody();
        assertThat(responseBoard.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("게시글 update")
    void update(){
        // given
        Long id = 101l;
        BoardDto boardDto = BoardDto.builder()
                .title("title Update")
                .content("content Update")
                .build();
        // when
        ResponseEntity<BoardDto> response = boardController.update(id, boardDto);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        BoardDto responseBoard = response.getBody();
        assertThat(responseBoard.getTitle()).isEqualTo(boardDto.getTitle());
        assertThat(responseBoard.getContent()).isEqualTo(boardDto.getContent());
    }

    @Test
    @DisplayName("게시글 ID로 삭제")
    void deleteById(){
        // given
        Long id = 101l;

        // when
        ResponseEntity response = boardController.delete(id);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThrows(IllegalArgumentException.class, () -> boardController.getBoardById(id));
    }
}