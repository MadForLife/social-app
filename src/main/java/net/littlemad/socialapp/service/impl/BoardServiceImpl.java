package net.littlemad.socialapp.service.impl;

import lombok.AllArgsConstructor;
import net.littlemad.socialapp.data.entity.Board;
import net.littlemad.socialapp.data.repository.BoardRepository;
import net.littlemad.socialapp.dto.BoardDTO;
import net.littlemad.socialapp.dto.CreateBoardDTO;
import net.littlemad.socialapp.dto.UpdateBoardDTO;
import net.littlemad.socialapp.mapper.BoardDTOMapper;
import net.littlemad.socialapp.service.BoardService;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardDTOMapper boardDTOMapper;

    @Override
    public List<BoardDTO> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(boardDTOMapper).collect(Collectors.toList());
    }

    // TODO Add ResourceNotFoundException
    @Override
    public BoardDTO getBoardById(String id) {
        return boardRepository.findById(id)
                .map(boardDTOMapper)
                .orElseThrow(() -> new RuntimeException(
                        "board with id [%s] not found".formatted(id)));
    }

    @Override
    public CreateBoardDTO createBoard(CreateBoardDTO createBoardDTO) {
        return null;
    }

    // TODO Implement Mapper for UpdateBoardDTO
    @Override
    public UpdateBoardDTO updateBoard(String id, UpdateBoardDTO updateBoardDTO) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "board with id [%s] not found".formatted(id)));

        board.setName(updateBoardDTO.getName());
        board.setPrivate(updateBoardDTO.isPrivate());
        boardRepository.save(board);

        UpdateBoardDTO result = new UpdateBoardDTO(
                board.getId(), board.getName(), board.isPrivate());
        return result;
    }

    //    @Override
//    public UpdateBoardDTO updateBoard(String id, UpdateBoardDTO updateBoardDTO) {
//        Board board = modelMapper.map(boardRepository.findById(id), Board.class);
//        board.setName(updateBoardDTO.getName());
//        board.setPrivate(updateBoardDTO.isPrivate());
//        return modelMapper.map(boardRepository.save(board), UpdateBoardDTO.class);
//    }

    // TODO Add ResourceNotFoundException
    @Override
    public void deleteBoard(String id) {
        if (!boardRepository.existsById(id)) {
            throw new RuntimeException("board with id [%s] not found".formatted(id));
        }

        boardRepository.deleteById(id);
    }



    //    @Override
//    public List<BoardDTO> getAllBoards() {
//        return this.boardRepository.findAll().stream()
//                .map(this::convertToBoardDTO)
//                .collect(Collectors.toList());
//
//    }
//
//    @Override
//    public BoardDTO getBoardById(String id) {
//        return modelMapper.map(boardRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Invalid board ID: " + id)), BoardDTO.class);
//    }
//
//    @Override
//    public CreateBoardDTO createBoard(User user,
//                                      CreateBoardDTO createBoardDTO) {
//        return modelMapper
//                .map(boardRepository
//                        .save(modelMapper
//                                .map(createBoardDTO, Board.class)), CreateBoardDTO.class);
//    }
//

}
