package wony.dev.board.BulletinBoard;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wony.dev.board.BulletinBoard.model.Board;

@RequestMapping("/board")
@RestController
public class BoardController {

    @PutMapping
    public void register(Board board){

    }
}
