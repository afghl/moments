package com.moments.repositories;

import com.moments.models.Following;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FollowingRepository extends PagingAndSortingRepository<Following, Integer> {
    List<Following> findByUserId(Integer id, Pageable pageRequest);
}
