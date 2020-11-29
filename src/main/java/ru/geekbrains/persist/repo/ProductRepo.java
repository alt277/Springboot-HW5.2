package ru.geekbrains.persist.repo;

import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// разновидность аннотации @Component.
@Repository           // Указывает что это компоненент  для доступа к ДАННЫМ
public class ProductRepo  {

  // внедрение -       аналог @Autowired
@PersistenceContext   //  позволяет работать с НЕСКОЛЬКИМИ источниками данных
private EntityManager  em;

public Product findMaxPrice(){
//   return em.createQuery("select p from Product p where p.price= ( select MAX (p.price) from Product p) ",Product.class).getSingleResult() ;
return em.createNamedQuery("Product.findPoductByMaxPrice",Product.class).getSingleResult() ;
}
public Product findMinPrice(){
return em.createQuery("select p from Product p where p.price= ( select MIN (p.price) from Product p) ",Product.class).getSingleResult() ;
//        return em.createNamedQuery("Product.findPoductByMaxPrice",Product.class).getSingleResult() ;
}
public List<Product> findMinMaxPrice(){
return em.createQuery("select p from Product p where p.price= ( select MAX (p.price) from Product p) or p.price= ( select MIN (p.price) from Product p)",Product.class).getResultList() ;
//        return em.createNamedQuery("Product.findPoductByMaxPrice",Product.class).getSingleResult() ;
}
}

