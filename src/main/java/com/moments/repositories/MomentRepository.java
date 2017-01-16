package com.moments.repositories;

import com.moments.models.Moment;
import com.moments.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface MomentRepository extends PagingAndSortingRepository<Moment, Long> {
    List<Moment> findFirst20ByUserIdAndIdLessThanOrderByIdDesc(Long userId, Long lastId);

    List<Moment> findByIdIn(Collection<Long> ids);
}
