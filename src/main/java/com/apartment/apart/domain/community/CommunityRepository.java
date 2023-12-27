package com.apartment.apart.domain.community;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
//    Community findBySubject(String title);
//    Page<Community> findAll(Pageable pageable);
}
