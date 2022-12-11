package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional

public interface PersonRepository extends JpaRepository<Person, Integer> {
    // Получаем запись из БД по логину
    Optional<Person> findByLogin(String login);

    @Modifying
    @Query(value = "UPDATE person SET role= 'ROLE_USER' WHERE id=?1", nativeQuery = true)
    void SetRoleUser(int id);

    @Modifying
    @Query(value = "UPDATE person SET role= 'ROLE_ADMIN' WHERE id=?1", nativeQuery = true)
    void SetRoleAdmin(int id);

}
