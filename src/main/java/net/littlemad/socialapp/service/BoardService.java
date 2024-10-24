package net.littlemad.socialapp.service;

import net.littlemad.socialapp.dto.BoardDTO;
import net.littlemad.socialapp.dto.CreateBoardDTO;
import net.littlemad.socialapp.dto.UpdateBoardDTO;

import java.util.List;

public interface BoardService {

    BoardDTO createBoard(CreateBoardDTO createBoardDTO);

    List<BoardDTO> getBoards();

    BoardDTO getBoardById(String id);

    BoardDTO updateBoardById(String id, UpdateBoardDTO updateBoardDTO);

    void deleteBoardById(String id);
}
