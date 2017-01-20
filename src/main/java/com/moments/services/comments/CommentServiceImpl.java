package com.moments.services.comments;

import com.moments.models.Comment;
import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;

    @Override
    public void mapComments(List<Moment> feed, User currentUser) {
        List<Comment> comments = repository.findByMomentIn(feed);

        feed.forEach((moment) -> {
            comments.forEach((comment) -> {
                if (comment.getMoment().equals(moment))
                    moment.getComments().add(comment);
            });
        });
    }
}
