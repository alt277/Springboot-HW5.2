package ru.geekbrains.persist.repo;


import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.Customer;
import ru.geekbrains.persist.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class CustomerRepo {
    // аналог @Autowired            - внедрение
    @PersistenceContext   //  позволяет работать с НЕСКОЛЬКИМИ источниками данных
    private EntityManager  em;
    public void update(Integer id, String first_name,String family_name) {
        em.createQuery("update Customer c set c.first_name =:first_name ,c.family_name =:family_name   where c.id=: id");
    }

}
