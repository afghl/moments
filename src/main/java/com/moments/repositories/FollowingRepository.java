package com.moments.repositories;

import com.moments.models.Following;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowingRepository extends PagingAndSortingRepository<Following, Long> {
    List<Following> findByUserId(Long id);

    @Transactional
    Long deleteByUserIdAndFollowerId(Long userId, Long followerId);
}
