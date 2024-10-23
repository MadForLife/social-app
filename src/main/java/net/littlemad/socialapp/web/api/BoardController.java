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
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public BoardDTO getBoardById(@PathVariable String id) {
        return boardService.getBoardById(id);
    }

    @PostMapping
    public CreateBoardDTO createBoardDTO(@RequestBody CreateBoardDTO createBoardDTO) {
        return boardService.createBoard(createBoardDTO);
    }

    @PutMapping("/{id}")
    public UpdateBoardDTO updateBoard(@PathVariable String id,
                                      @RequestBody UpdateBoardDTO updateBoardDTO) {
        return boardService.updateBoard(id, updateBoardDTO);
    }

    @DeleteMapping
    public void deleteBoardById(@PathVariable String id) {
        boardService.deleteBoard(id);
    }

}
