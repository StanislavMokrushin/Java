package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    // Получаем запись из БД по логину
    Optional<Person> findByLogin(String login);


//    @Modifying
//    @Query(value = "UPDATE person SET role= 'ROLE_USER' WHERE id=?1", nativeQuery = true)
//    void SetRoleUser(int id);
//
//    @Modifying
//    @Query(value = "UPDATE person SET role= 'ROLE_ADMIN' WHERE id=?1", nativeQuery = true)
//    void SetRoleAdmin(int id);
//
//    @Modifying
//    @Query(value = "UPDATE person SET role= 'ROLE_SELLER' WHERE id=?1", nativeQuery = true)
//    void SetRoleSeller(int id);

    @Modifying
    @Query(value = "UPDATE person SET password = ?2 WHERE id= ?1", nativeQuery = true)
    void updatePersonById(int id, String password);

}
