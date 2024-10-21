package net.littlemad.socialapp.service;

import net.littlemad.socialapp.dto.BoardDTO;
import net.littlemad.socialapp.dto.CreateBoardDTO;
import net.littlemad.socialapp.dto.UpdateBoardDTO;

import java.util.List;

public interface BoardService {

    List<BoardDTO> getAllBoards();

    BoardDTO getBoardById(String id);
    CreateBoardDTO createBoard(CreateBoardDTO createBoardDTO);
    UpdateBoardDTO updateBoard(String id, UpdateBoardDTO updateBoardDTO);
    void deleteBoard(String id);

}
