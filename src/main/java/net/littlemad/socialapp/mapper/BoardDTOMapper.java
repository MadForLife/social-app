package net.littlemad.socialapp.mapper;

import net.littlemad.socialapp.data.entity.Board;
import net.littlemad.socialapp.dto.BoardDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BoardDTOMapper implements Function<Board, BoardDTO> {

    @Override
    public BoardDTO apply(Board board) {
        return new BoardDTO(board.getId(), board.getName());
    }




}
