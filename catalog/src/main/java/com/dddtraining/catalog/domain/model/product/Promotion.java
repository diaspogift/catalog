package com.dddtraining.catalog.domain.model.product;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;
import java.time.ZonedDateTime;

@Embeddable
public class Promotion {
    @Transient
    @Embedded
    private Discount discount;

    @Embedded
    private LifeSpan lifeSpan;

    @Column(nullable = true)
    private int strategy;

    public Promotion() {
    }

    public Promotion(Discount discount, LifeSpan lifeSpan) {
        this.setDiscount(discount);
        this.setLifeSpan(lifeSpan);
    }

    public void setDiscount(Discount discount){
        this.discount = discount;
    }

    private void setLifeSpan(LifeSpan lifeSpan){
        this.lifeSpan = lifeSpan;
    }

    public Discount getDiscount() {
        return discount;
    }

    public LifeSpan getLifeSpan() {
        return lifeSpan;
    }

    public int getStrategy() {
        return strategy;
    }

    public void setStrategy(int strategy) {
        this.strategy = strategy;
    }

    /*
        LOGIC
     */
    public boolean inCourse(){
        ZonedDateTime now = ZonedDateTime.now();
        if (now.isBefore(lifeSpan.ZendDate()) && now.isAfter(lifeSpan.ZstartDate())){
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "\n\nPromotion{" +
                "discount=" + discount +
                ", lifeSpan=" + lifeSpan +
                ", strategy=" + strategy +
                '}' + "\n\n";
    }
}
