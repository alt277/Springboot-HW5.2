package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import ru.geekbrains.persist.entity.Customer;
import ru.geekbrains.persist.entity.Product;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>  {
   @Query("select c from Customer c where c.first_name = :first_name  ")
   List<Customer> findByName(@Param("first_name") String first_name);
   @Query("select c from Customer c where c.first_name = 'Alex'  ")
   List<Customer> findByName2();
//   @Query("select c from Customer c where (c.first_name = :first_name or c.first_name is null)  or" +
//           "(c.family_name = :family_name or c.family_name is null) ")
//   List<Customer> findByName3(@Param("first_name") String first_name, @Param("family_name") String family_name ) ;
   @Query("select c from Customer c where (c.first_name = :first_name ) or" +
           "(c.family_name = :family_name )")
   List<Customer> findByNameAndFamily_name(@Param("first_name") String first_name, @Param("family_name") String family_name ) ;

   @Query("select p from Product p where  p.title=:title or p.title is null")
   List<Product> findByQueryTitle(@Param("title") String title);

//
//   @Query(" update Customer c set c.first_name = :first_name , c.family_name = :family_name   where c.id=:id")
//  void update( @Param("id") Integer id,@Param("first_name") String first_name, @Param("family_name") String family_name );

}

