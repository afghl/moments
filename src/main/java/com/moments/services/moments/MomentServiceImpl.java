package com.moments.services.moments;

import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.repositories.MomentRepository;
import com.moments.services.users.UserService;
import com.moments.utils.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.moments.utils.RedisKeysPresenter.userFeedCacheKey;

@Service
public class MomentServiceImpl implements MomentService {

    @Autowired
    private MomentRepository moments;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisHelper redisHelper;

    public List<Moment> findMomentsOfUser(User user, Long lastMomentId) {
        return moments.findFirst20ByUserIdAndIdLessThanOrderByIdDesc(user.getId(), lastMomentId);
    }


    public Moment save(Moment moment) {
        return moments.save(moment);
    }

    @Override
    public Moment saveAndPubToFollowers(Moment m, User u) {
        m.setUser(u);
        m = moments.save(m);

        publishMomentId(m.getId(), u.getId());

        return m;
    }

    // TODO: aop?
    private void publishMomentId(Long id, Long userId) {
        List<User> followers = userService.findFollowers(userId, true);
        followers.forEach((user) ->
            redisHelper.addIdToSortedSet(
                    userFeedCacheKey(user), id)
        );
    }

    @Override
    public List<Long> findMomentIdsOfUsers(List<User> users) {
        // TODO: just select id column

        return moments
                .findByUserIn(users)
                .stream()
                .map(Moment::getId)
                .collect(Collectors.toList());
    }

    @Override
    public Moment findOne(Long id) {
        return moments.findOne(id);
    }
}
