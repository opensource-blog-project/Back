package com.example.opensource_blog.service.likes;

import com.example.opensource_blog.domain.likes.Like;
import com.example.opensource_blog.domain.likes.LikeRepository;
import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.post.PostRepository;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostLikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void pushLike(int postId,String username) {
        log.info("{} : {}",getClass().getSimpleName(),"pushLike(Long,String)");

        var post = postRepository.findByPostId(postId).orElseThrow(EntityNotFoundException::new);
        var usrAccount =  userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        Optional<Like> alreadyPushedLike = alreadyPushedLike(post, usrAccount);

        if(alreadyPushedLike.isPresent()) {
            likeRepository.delete(alreadyPushedLike.get());
        }else {
            Like like = Like.of(post, usrAccount);
            likeRepository.save(like);
        }
    }
    private Optional<Like> alreadyPushedLike(Post post, UserAccount userAccount) {
        log.info("이미 눌린 '좋아요'인지 체크하는 로직 실행");
        return likeRepository.findByPostAndUser(post,userAccount);
    }
}
