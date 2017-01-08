package com.moments.services.moments;

import com.moments.models.Moment;
import com.moments.models.User;
import com.moments.repositories.MomentRepository;
import com.moments.services.moments.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MomentServiceImpl implements MomentService {

    @Autowired
    private MomentRepository moments;

    public List<Moment> findMomentsOfUser(User user, Integer lastMomentId) {
        return moments.findFirst20ByUserIdAndIdGreaterThan(user.getId(), lastMomentId);
    }
}
