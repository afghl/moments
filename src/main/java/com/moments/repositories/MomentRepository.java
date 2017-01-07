package com.moments.repositories;

import com.moments.models.Moment;
import com.moments.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MomentRepository extends PagingAndSortingRepository<Moment, Integer> {
    List<Moment> findFirst20ByUserIdAndIdGreaterThan(Integer userId, Integer lastId);
}
