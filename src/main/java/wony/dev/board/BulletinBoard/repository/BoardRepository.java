package wony.dev.board.BulletinBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wony.dev.board.BulletinBoard.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
