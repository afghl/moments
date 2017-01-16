package com.moments.services.moments;

import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.repositories.MomentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MomentServiceImpl implements MomentService {

    @Autowired
    private MomentRepository moments;

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

        publishMomentId(m.getId(), u);

        return m;
    }

    private void publishMomentId(Long id, User u) {
        // TODO
        return;
    }

    public List<Moment> findMomentsOfUsers(List<User> users, Long lastMomentId) {
        List<Long> ids = users
                            .stream()
                            .map(User::getId)
                            .collect(Collectors.toList());

        System.out.println(ids);
        return moments.findFirst20ByUserIdInAndIdLessThanOrderByIdDesc(ids, lastMomentId);
    }
}
