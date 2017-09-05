package com.dddtraining.catalog.domain.model.product;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.time.ZonedDateTime;

@Embeddable
public class LifeSpan {
    @Transient
    private ZonedDateTime ZstartDate;
    @Transient
    private ZonedDateTime ZendDate;

    private String startDate;
    private String endDate;

    public LifeSpan(ZonedDateTime startDate, ZonedDateTime endDate) {

        if (startDate.isBefore(endDate)){
            this.setZStartDate(startDate);
            this.setZEndDate(endDate);
            return;
        }

       throw new IllegalArgumentException("Invalide Argument exception: The first argument must be a date striclly before the second argument");

    }

    public LifeSpan() {
    }

    public ZonedDateTime ZstartDate(){
        return this.ZstartDate;
    }

    public ZonedDateTime ZendDate(){
        return this.ZendDate;
    }

    private void setZStartDate(ZonedDateTime startDate) {
        this.ZstartDate = startDate;
        this.startDate = ZstartDate.toString();
    }

    private void setZEndDate(ZonedDateTime endDate) {

        this.ZendDate = endDate;
        this.endDate = ZendDate.toString();
    }

    @Override
    public String toString() {
        return "\n\nLifeSpan{" +
                "startDate=" +  startDate+
                ", endDate=" + endDate +
                '}' + "\n\n";
    }
}
