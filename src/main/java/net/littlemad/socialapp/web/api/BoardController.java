package net.littlemad.socialapp.web.api;

import lombok.AllArgsConstructor;
import net.littlemad.socialapp.dto.BoardDTO;
import net.littlemad.socialapp.dto.CreateBoardDTO;
import net.littlemad.socialapp.dto.UpdateBoardDTO;
import net.littlemad.socialapp.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardDTO> getBoards() {
        return boardService.getBoards();
    }

    @PostMapping
    public BoardDTO createBoard(@RequestBody CreateBoardDTO createBoardDTO) {
        return boardService.createBoard(createBoardDTO);
    }

    @GetMapping("/{id}")
    public BoardDTO getBoardByID(@PathVariable String id) {
        return boardService.getBoardById(id);
    }

    @PutMapping("/{id}")
    public BoardDTO updateBoardById(@PathVariable String id,
                                    @RequestBody UpdateBoardDTO updateBoardDTO) {
        return boardService.updateBoardById(id, updateBoardDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBoardById(@PathVariable String id) {
        boardService.deleteBoardById(id);
    }


}
