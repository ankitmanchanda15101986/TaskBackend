package com.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.task.common.entity.TaskEntity;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, Integer> {
	
	@Query(value="From TaskEntity where status=:status")
	public List<TaskEntity> findBasedOnStatus(@Param("status") String status);
	
}
