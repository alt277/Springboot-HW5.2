package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> ,JpaSpecificationExecutor<Product>{

    List<Product> findByTitleLike(String title);
    List<Product> findMaxPrice();
    List<Product> findByTitleOrderByPriceDesc(String title);


    /*  если НЕДОСТАТОЧНО стандартных методов методов JpaRepository
          аннотация - @Query  указывает Spring чтобы он САМОСТОЯТЕЛЬНО создал ОБЪЕКТ КЛАССА,
          реализующего интерфейс JpaRepository
                  и указаваем свой запрос:                    */
    @Query("select p from Product p where p.price= ( select MIN (p.price) from Product p) ")
    Product findMinPrice();

     /*           если есть параметр у функции
                  обязательно указать имя столбца в @Param("title"),
                   которое присваиваивается переменной в запросе             */
    @Query("select p from Product p where  p.title=:title or p.title is null")
    List<Product> findByQueryTitle(@Param("title") String title);
}

