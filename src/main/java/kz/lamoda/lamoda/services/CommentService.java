package kz.lamoda.lamoda.services;

import kz.lamoda.lamoda.models.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);
    Comment update(Comment comment);
    List<Comment> findAll ();
    Comment findById(Long id);
    void delete(Long id);
}
