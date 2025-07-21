package com.example.repository;

import com.example.entity.Micropost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MicropostRepository extends JpaRepository<Micropost, Long> {
    @Query("SELECT m FROM Micropost m JOIN FETCH m.user ORDER BY m.createdAt DESC")
    List<Micropost> findAllWithUser();
    
    @Query("SELECT m FROM Micropost m JOIN FETCH m.user WHERE m.id = :id")
    Optional<Micropost> findByIdWithUser(@Param("id") Long id);
}
