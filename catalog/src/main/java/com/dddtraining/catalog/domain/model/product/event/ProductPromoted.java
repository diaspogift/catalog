package com.dddtraining.catalog.domain.model.product.event;

import com.dddtraining.catalog.domain.model.product.Promotion;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
public class ProductPromoted implements DomainEvent , Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer nativeId;
    private String productId;
    private BigDecimal price;
    private BigDecimal promotionPrice;
    private Promotion promotion;
    @Transient
    private ZonedDateTime ZoccurredOn;
    private String occurredOn;

    @Override
    public int eventVersion() {
        return 1;
    }

    @Override
    public ZonedDateTime occurredOn() {
        ZonedDateTime now = ZonedDateTime.now();
        this.occurredOn = now.toString();
        return now;
    }

    public ProductPromoted(Integer nativeId, String productId, BigDecimal price, BigDecimal promotionPrice, Promotion promotion, ZonedDateTime occurredOn) {
        this.nativeId = nativeId;
        this.productId = productId;
        this.price = price;
        this.promotionPrice = promotionPrice;
        this.promotion = promotion;
        this.ZoccurredOn = occurredOn;
        this.occurredOn = this.ZoccurredOn.toString();
    }


    public ProductPromoted(String productId, BigDecimal price, BigDecimal promotionPrice, Promotion promotion) {
        this.productId = productId;
        this.price = price;
        this.promotionPrice = promotionPrice;
        this.promotion = promotion;
        this.ZoccurredOn = this.occurredOn();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Integer getNativeId() {
        return nativeId;
    }

    public void setNativeId(Integer nativeId) {
        this.nativeId = nativeId;
    }

    public ZonedDateTime getZOccurredOn() {
        return ZoccurredOn;
    }

    public void setZOccurredOn(ZonedDateTime occurredOn) {
        this.ZoccurredOn = occurredOn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }


    @Override
    public String toString() {
        return "ProductPromoted{" +
                "nativeId=" + nativeId +
                ", productId='" + productId + '\'' +
                ", price=" + price +
                ", promotionPrice=" + promotionPrice +
                ", promotion=" + promotion +
                ", ZoccurredOn=" + ZoccurredOn +
                ", occurredOn='" + occurredOn + '\'' +
                '}';
    }
}
