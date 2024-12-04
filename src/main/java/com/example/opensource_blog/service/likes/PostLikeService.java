package com.example.opensource_blog.service.likes;

import com.example.opensource_blog.domain.post.PostRepository;
import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.likes.Like;
import com.example.opensource_blog.domain.likes.LikeRepository;

import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.domain.users.UserRepository;
import com.example.opensource_blog.service.user.UserInfo;
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
    public void pushLike(int postId, UserInfo userInfo) {
        log.info("{} : {}",getClass().getSimpleName(),"pushLike(Long,String)");

        var post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
        var usrAccount =  userRepository.findByUserId(userInfo.getUsername()).orElseThrow(EntityNotFoundException::new);
        Optional<Like> alreadyPushedLike = alreadyPushedLike(post, usrAccount);

        if(alreadyPushedLike.isPresent()) {
            likeRepository.delete(alreadyPushedLike.get());
        }else {
            Like like = Like.of(post, usrAccount);
            likeRepository.save(like);
        }
    }
    public int getLikeCountByPostId(int postId) {
        if(!postRepository.existsById(postId))
            throw new IllegalArgumentException("post not founded"+postId);
        return likeRepository.countByPost_id(postId);
    }
    private Optional<Like> alreadyPushedLike(Post post, UserAccount userAccount) {
        log.info("이미 눌린 '좋아요'인지 체크하는 로직 실행");
        return likeRepository.findByPostAndUser(post,userAccount);
    }
}
