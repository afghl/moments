package com.moments.services.comments;

import com.moments.models.Moment;
import com.moments.models.User;

import java.util.List;

public interface CommentService {
    void mapComments(List<Moment> feed, User currentUser);
}
