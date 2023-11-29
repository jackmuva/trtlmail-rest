package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.Subscription;
import com.jackmu.slowcapsules.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Long> {
    List<Writer> findAllByPenName(String penName);
    List<Writer> findAllByWriterId(Long id);
    List<Writer> findAllByEmail(String email);
}
