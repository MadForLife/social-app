package net.littlemad.socialapp.data.repository;

import net.littlemad.socialapp.data.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, String> {
}
