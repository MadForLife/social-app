package net.littlemad.socialapp.mapper;

import net.littlemad.socialapp.data.entity.Board;
import net.littlemad.socialapp.data.entity.User;
import net.littlemad.socialapp.dto.BoardDTO;
import net.littlemad.socialapp.dto.CreateBoardDTO;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Function;

@Service
public class BoardDTOMapper {

    public Function<Board, BoardDTO> toBoardDTO() {
        return board -> new BoardDTO(
                board.getId(),
                board.getName(),
                board.isPrivate());
    }

    public BiFunction<CreateBoardDTO, User, Board> fromCreateBoardDTO() {
        return (createBoardDTO, user) -> {

            if (createBoardDTO == null || user == null) {
                throw new IllegalArgumentException("createBoardDTO or user is null");
            }

            Board board = new Board();
            board.setName(createBoardDTO.getName());
            board.setPrivate(createBoardDTO.isPrivate());
            board.setAuthor(user);

            return board;
        };
    }
}
