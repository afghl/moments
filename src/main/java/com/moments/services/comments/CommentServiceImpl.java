package com.moments.services.comments;

import com.moments.models.Comment;
import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository comments;

    @Override
    public Comment replyOnMoment(Integer type, String body, Moment m, User user, User other) {
        // TODO: validate if user can reply on this moment.
        // TODO: validate if user already like this moment.
        Comment c = new Comment();

        c.setMoment(m);
        c.setType(Long.valueOf(type));
        c.setBody(body);
        c.setUserId(user.getId());
        c.setUserName(user.getName());
        c.setUserAvatar(user.getAvatar());
        if (null != other) {
            c.setOtherId(other.getId());
            c.setOtherName(other.getName());
            c.setOtherAvatar(other.getAvatar());
        }

        comments.save(c);

        return c;
    }
}
