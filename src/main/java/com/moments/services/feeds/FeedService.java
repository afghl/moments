package com.moments.services.feeds;

import com.moments.models.Moment;
import com.moments.models.User;

import java.util.List;

public interface FeedService {
    public List<Moment> findFeedsOfUser(User u, int limit, Long lastMomentId);
}
