package com.moments.repositories;

import com.moments.models.Moment;
import com.moments.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MomentRepository extends PagingAndSortingRepository<Moment, Long> {
    List<Moment> findFirst20ByUserIdAndIdGreaterThan(Long userId, Long lastId);

    List<Moment> findFirst20ByUserIdInAndIdGreaterThan(List<Long> ids, Long lastId);
}
