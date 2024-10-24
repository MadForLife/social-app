package net.littlemad.socialapp.service.impl;

import lombok.AllArgsConstructor;
import net.littlemad.socialapp.data.entity.Board;
import net.littlemad.socialapp.data.entity.User;
import net.littlemad.socialapp.data.repository.BoardRepository;
import net.littlemad.socialapp.data.repository.UserRepository;
import net.littlemad.socialapp.dto.BoardDTO;
import net.littlemad.socialapp.dto.CreateBoardDTO;
import net.littlemad.socialapp.dto.UpdateBoardDTO;
import net.littlemad.socialapp.mapper.BoardDTOMapper;
import net.littlemad.socialapp.service.BoardService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardDTOMapper boardDTOMapper;

    @Override
    public List<BoardDTO> getBoards() {
        return boardRepository.findAll().stream()
                .map(board -> boardDTOMapper.toBoardDTO()
                        .apply(board)).collect(Collectors.toList());
    }

    @Override
    public BoardDTO createBoard(CreateBoardDTO createBoardDTO) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());

        return boardDTOMapper.toBoardDTO().apply(
                boardRepository.save(boardDTOMapper.fromCreateBoardDTO()
                        .apply(createBoardDTO, user)));

    }

    @Override
    public BoardDTO getBoardById(String id) {
        return boardDTOMapper.toBoardDTO().apply(boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "board with id [%s] not found".formatted(id))));
    }

    @Override
    public BoardDTO updateBoardById(String id, UpdateBoardDTO updateBoardDTO) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("board with id [%s] not found".formatted(id)));

        board.setName(updateBoardDTO.getName());
        board.setPrivate(updateBoardDTO.isPrivate());

        return boardDTOMapper.toBoardDTO().apply(boardRepository.save(board));
    }

    // TODO Additional confirmation (check if entity exists)
    @Override
    public void deleteBoardById(String id) {
        boardRepository.deleteById(id);
    }
}
