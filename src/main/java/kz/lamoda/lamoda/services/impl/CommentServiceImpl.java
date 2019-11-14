package kz.lamoda.lamoda.services.impl;

import kz.lamoda.lamoda.models.Comment;
import kz.lamoda.lamoda.repositories.CommentRepository;
import kz.lamoda.lamoda.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        if(comment.getId()==null){
            return commentRepository.save(comment);
        }
        return null;
    }

    @Override
    public Comment update(Comment comment) {
        if(comment.getId()!=null){
            return commentRepository.save(comment);
        }
        return null;
    }

    @Override
    public List<Comment> findAll() {

        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {

        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
