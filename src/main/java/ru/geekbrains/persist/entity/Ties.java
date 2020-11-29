package ru.geekbrains.persist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ties")
public class Ties{
@Id
@Column
private Integer id;

@Column
private Integer product_id;

@Column
private Integer customer_id;

public Ties(){};
public Ties(Integer id,Integer customer_id,Integer product_id){
this.id=id;
this.customer_id=customer_id;
this.product_id=product_id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getId() {
return id;
}

public Integer getCustomer_id() {
return customer_id;
}
public void setCustomer_id(Integer customer_id) {
this.customer_id = customer_id;
}

public Integer getProduct_id() {
return product_id;
}
public void setProduct_id(Integer product_id) {
this.product_id = product_id;
}

@Override
public String toString() {
return "Ties{" +
"id=" + id +
", product_id=" + product_id +
", customer_id=" + customer_id +
'}';
}
}
