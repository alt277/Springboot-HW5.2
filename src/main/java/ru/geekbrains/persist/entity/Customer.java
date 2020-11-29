package ru.geekbrains.persist.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Customer")

        public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "family_name")
    private String family_name;

    @ManyToMany
    @JoinTable(
            name = "ties",
            joinColumns=@JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")

)
    private List<Product> products;

    public Customer(){};
    public Customer(Integer id,String first_name,String family_name,List<Product> products){
        this.id=id;
        this.first_name=first_name;
        this.family_name=family_name;
        this.products=products;
    }

    public Customer(String first_name,String family_name){

        this.first_name=first_name;
        this.family_name=family_name;
    }

    public Customer(Integer id,String first_name,String family_name){
        this.id=id;
        this.first_name=first_name;
        this.family_name=family_name;
    }

    public void setId(Integer id) { this.id = id; }
    public Integer getId() {
        return id;
    }


    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getFirst_name() {
        return first_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }
    public String getFamily_name() {
        return family_name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", family_name='" + family_name + '\'' +
                '}'+   '\n';     // -добавим, чтобы разделять вывод
    }

//    @Override
//    public String toString() {
//        return "Customer{" +
//                "id=" + id +
//                ", first_name='" + first_name + '\'' +
//                ", family_name='" + family_name + '\'' +
//                ", products=" + products +  // вывод листа продуктов приведет
//                                            // к переполнеию сложном запросе
//                '}'+'\n';
//    }
}

