package ru.geekbrains.persist.entity;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Product")  //@Table javax.persistence не HIBERNATE
@NamedQueries( {
        @NamedQuery(name = "Product.findMaxPrice",query ="SELECT max(p.price) from Product p") ,
@NamedQuery(name = "Product.findPoductByMaxPrice",query ="select p from Product p where p.price= ( select MAX (p.price) from Product p)" )
        })
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany
    @JoinTable(
            name = "ties",
            joinColumns=@JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )

    private List<Customer> customers;

    public Product(){}
    public  Product(Integer id,String title, BigDecimal price,List<Customer> customers){
        this.id=id;
        this.title=title;
        this.price=price;
        this.customers=customers;
    }
    public  Product(Integer id,String title,BigDecimal price){
        this.id=id;
        this.title=title;
        this.price=price;
    }
    public void setId(Integer id) {
        this.id = id; }
    public int getId() {
        return id; }

    public void setTitle(String title) {
        this.title = title; }
    public String getTitle() {
        return title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price; }
    public BigDecimal getPrice() {
        return price;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}' +  '\n';     // -добавим, чтобы разделять вывод      ;
    }
//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", price=" + price +
//                ", customers=" + customers +
//                '}';
//    }
}
