package com.example.demo.customer;

import javax.persistence.*;

@Entity
public class CreditCard {
    @Id
    private long card_number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_email", referencedColumnName = "email")
    private Customer customer;

    public CreditCard(){
    }

    public CreditCard(long card_number) {
        this.card_number = card_number;
    }

    public long getCard_number() {
        return card_number;
    }

    public void setCard_number(long card_number) {
        this.card_number = card_number;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "card_number=" + card_number +
                '}';
    }

    public void assignCustomer(Customer customer) {
        this.customer = customer;
    }
}
