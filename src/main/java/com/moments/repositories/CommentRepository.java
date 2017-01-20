package com.moments.repositories;

import com.moments.models.Comment;
import com.moments.models.Following;
import com.moments.models.Moment;
import com.moments.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMomentIn(Collection<Moment> moments);
}
