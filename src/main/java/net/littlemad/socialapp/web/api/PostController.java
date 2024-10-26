package net.littlemad.socialapp.web.api;

import lombok.AllArgsConstructor;
import net.littlemad.socialapp.dto.PostDTO;
import net.littlemad.socialapp.service.PostService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static net.littlemad.socialapp.config.Constant.MEDIA_DIRECTORY;
import static org.springframework.util.MimeTypeUtils.*;

@AllArgsConstructor

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public PostDTO createPost(@RequestParam("title") String title,
                              @RequestParam("description") String description,
                              @RequestParam("media")MultipartFile media) {

        return postService.createPost(new PostDTO(title, description, null), media);
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable String id) {
        postService.deletePostById(id);
    }

    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE, IMAGE_GIF_VALUE })
    public byte[] getMedia(@PathVariable("filename") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(MEDIA_DIRECTORY + fileName));
    }

}
