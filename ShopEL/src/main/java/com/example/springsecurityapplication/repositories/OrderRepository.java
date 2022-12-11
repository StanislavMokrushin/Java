package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {
//    по пользователю извлекаем все заказы
    List<Order> findByPerson(Person person);
//    List<Order> findAll();

//    List<Order> findByNumberContainingIgnoreCase(String name);
    List<Order> findByNumberEndingWith(String endingWith);

}
