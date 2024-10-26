package net.littlemad.socialapp.service;

import net.littlemad.socialapp.dto.PostDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO, MultipartFile media);

    PostDTO getPostById(String id);

    void deletePostById(String id);

    List<PostDTO> getPosts();
}
