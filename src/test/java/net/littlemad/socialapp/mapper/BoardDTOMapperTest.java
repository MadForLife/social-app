package net.littlemad.socialapp.mapper;

import net.littlemad.socialapp.data.entity.Board;
import net.littlemad.socialapp.dto.BoardDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardDTOMapperTest {

    private BoardDTOMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new BoardDTOMapper();
    }

    @Test
    public void shouldMapBoardToBoardDTO() {

        Board board = new Board("Default Board", true);

        BoardDTO boardDTO = mapper.toBoardDTO().apply(board);

        Assertions.assertEquals(board.getName(),boardDTO.getName());
        Assertions.assertEquals(board.isPrivate(),boardDTO.isPrivate());

    }

}