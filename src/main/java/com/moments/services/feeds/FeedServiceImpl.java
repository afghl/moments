package com.moments.services.feeds;

import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.repositories.MomentRepository;
import com.moments.utils.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.moments.utils.RedisKeysPresenter.userFeedCacheKey;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private MomentRepository moments;

    @Override
    public List<Moment> findFeedsOfUser(User u, int l, Long id) {
        // TODO: do not use type casting.
        int idvalue = id.intValue() == -1 ? Integer.MAX_VALUE : id.intValue();

        List<Long> ids = redisHelper.getSortedSet(
                userFeedCacheKey(u), l, idvalue
        );
        
        return moments.findByIdInOrderByIdDesc(ids);
    }
}
