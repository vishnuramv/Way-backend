package com.example.way.likes;

import com.example.way.post.Post;
import com.example.way.post.PostRepository;
import com.example.way.user.User;
import com.example.way.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LikesService {
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public void upVotePosts(String postId) throws NullPointerException{
        UserDetails userreq = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.getById(postId);
        User user = userRepository.getById(userreq.getUsername());
        Likes likes = new Likes();
        if( !(likesRepository.existsByUserAndPost(user, post)) ){
            likes.setPost(post);
            likes.setUser(user);
            likesRepository.save(likes);
            post.setUpVotes(post.getUpVotes()+1);
            postRepository.save(post);
        }
    }
    public void unUpVotePosts(String postId) throws NullPointerException{
        UserDetails userreq = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getById(userreq.getUsername());
        Post post = postRepository.getById(postId);
        if( likesRepository.existsByUserAndPost(user, post) ){
            Likes likes = likesRepository.findByUserAndPost(user, post);
            likesRepository.delete(likes);
            post.setUpVotes(post.getUpVotes()-1);
            postRepository.save(post);
        } else {
            throw new NullPointerException("Post not found");
        }
    }
    public void unDownVotePost(String postId) throws NullPointerException{
        UserDetails userreq = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getById(userreq.getUsername());
        Post post = postRepository.getById(postId);
        if( likesRepository.existsByUserAndPost(user, post) ){
            Likes likes = likesRepository.findByUserAndPost(user, post);
            likesRepository.delete(likes);
            post.setDownVotes(post.getDownVotes()-1);
            postRepository.save(post);
        } else {
            throw new NullPointerException("Post not found");
        }
    }
    public void downVotePosts(String postId) throws NullPointerException{
        UserDetails userreq = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getById(userreq.getUsername());
        Post post = postRepository.getById(postId);
        Likes likes = new Likes();
        if( !likesRepository.existsByUserAndPost(user, post) ){
            likes.setPost(post);
            likes.setUser(user);
            likesRepository.save(likes);
            post.setDownVotes(post.getDownVotes()+1);
            postRepository.save(post);
        }
    }
}
