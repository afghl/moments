package com.moments.services.moments;

import com.moments.models.Moment;
import com.moments.models.User;

import java.util.List;

public interface MomentService {
    List<Moment> findMomentsOfUser(User user, Long lastMomentId);
}
