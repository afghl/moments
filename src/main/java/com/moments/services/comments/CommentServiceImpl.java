package com.moments.services.comments;

import com.moments.models.Comment;
import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;

    @Override
    public void mapComments(List<Moment> feed, User currentUser) {
        System.out.println(feed.size());
        System.out.println(feed);
        List<Comment> comments = repository.findByMomentIn(feed);
        System.out.println(comments.size());
        System.out.println(comments);
        feed.forEach((moment) -> {
            comments.forEach((comment) -> {
                if (comment.getMoment().equals(moment))
                    moment.getComments().add(comment);
            });
        });
    }
}
