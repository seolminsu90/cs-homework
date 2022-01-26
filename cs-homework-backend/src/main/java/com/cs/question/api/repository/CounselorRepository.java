package com.cs.question.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.cs.question.api.entity.Counselor;

public interface CounselorRepository extends CrudRepository<Counselor, String> {
}
