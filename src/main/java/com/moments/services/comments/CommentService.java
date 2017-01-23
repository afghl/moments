package com.moments.services.comments;

import com.moments.models.Comment;
import com.moments.models.Moment;
import com.moments.models.User;


public interface CommentService {
    Comment replyOnMoment(Integer type, String body, Moment m, User user, User other) throws UserCannotReplyMoment;
}
