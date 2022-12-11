//package com.example.springsecurityapplication.repositories;
//
//import com.example.springsecurityapplication.models.Product;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.security.core.parameters.P;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//
//@Repository
//public interface ProductRepository extends JpaRepository<Product, Integer> {
////   пописк товара по наименованию (opptional - когда не факт что вернется что то)
//   Optional<Product> findByTitle(String title);
////    поиск по части наименования товара в не зависимости от регистрации, контаининг - это то что может содержаться в переменной нэйм, игнор кейс - игнорируется регистр
//    List<Product> findByTitleContainingIgnoreCase(String name);
////    поиск по части наименования товара и фильтрация по диапазону цен, @Query - привязать запрос скл к методу, % означает что может быть текс как слева так и справо(%?1 - только слева, ?1% - только справа их надо в ковычках уже, когда с обоих сторон ковычки не нужны), (lower(title) LIKE %?1%) - сравни титл в нижнем регистре с масокй
//    @Query(value = "select * from product where CASE ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1') and (price >= ?2 and price <= ?3))", nativeQuery = true)
//    List<Product> findByTitleAndPriceGreaterThanEqualAndPriceLessThan(String title, float ot, float Do);
//
//
//    //    поиск по части наименования товара и фильтрация по диапазону цен, сортировка по возврастанию
//    @Query(value = "select * from product where CASE ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1') and (price >= ?2 and price <= ?3) order by price)", nativeQuery = true)
//    List<Product> findByTitleOrderByPrice(String title, float ot, float Do);
//
//    //    поиск по части наименования товара и фильтрация по диапазону цен, сортировка по убыванию
//    @Query(value = "select * from product where CASE ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1') and (price >= ?2 and price <= ?3) order by price desc)", nativeQuery = true)
//    List<Product> findByTitleOrderByPriceDesc(String title, float ot, float Do);
//
//    //    поиск по части наименования товара и фильтрация по диапазону цен, сортировка по возврастанию + фильтрация по категории
//    @Query(value = "select * from product where CASE ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3) and category_id=?4 order by price", nativeQuery = true)
//    List<Product> findByTitleAndCategoryOrderByPrice(String title, float ot, float Do, int category);
//
//    //    поиск по части наименования товара и фильтрация по диапазону цен, сортировка по убыванию + фильтрация по категории
//    @Query(value = "select * from product where CASE ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3) and category_id=?4 order by price desc", nativeQuery = true)
//    List<Product> findByTitleAndCategoryOrderByPriceDesc(String title, float ot, float Do, int category);
//}
package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByTitle(String title);
    // Поиск по части наименования товара в не зависимости от регистра
    List<Product> findByTitleContainingIgnoreCase(String name);

    // Поиск по части наименования товара и фильтрация по диапазону цен
    @Query(value = "select * from product where (((lower(title) LIKE (%?1%)) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and ((price >= (?2)) and (price <= (?3))))", nativeQuery = true)
    List<Product> findByTitleAndPriceGreaterThanEqualAndPriceLessThan(String title, float ot, float Do);

    // Поиск по части наименования товара и фильтрация по диапазону цен, сортировка по возрастанию
    @Query(value = "select * from product where (((lower(title) LIKE (%?1%)) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and ((price >= (?2)) and (price <= (?3)))) order by price", nativeQuery = true)
    List<Product> findByTitleOrderByPrice(String title, float ot, float Do);

    // Поиск по части наименования товара и фильтрация по диапазону цен, сортировка по убыванию
    @Query(value = "select * from product where (((lower(title) LIKE (%?1%)) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and ((price >= (?2)) and (price <= (?3)))) order by price desc", nativeQuery = true)
    List<Product> findByTitleOrderByPriceDesc(String title, float ot, float Do);


    // Поиск по части наименования товара и фильтрация по диапазону цен, сортировка по возрастанию, фильтрация по категории
    @Query(value = "select * from product where ((category_id= (?4)) and ((lower(title) LIKE (%?1%)) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= (?2)) and (price <= (?3))) order by price", nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPrice(String title, float ot, float Do, int category);


    // Поиск по части наименования товара и фильтрация по диапазону цен, сортировка по убыванию, фильтрация по категории
    @Query(value = "select * from product where category_id=?4 and (((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3)) order by price desc", nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPriceDesc(String title, float ot, float Do, int category);






}
