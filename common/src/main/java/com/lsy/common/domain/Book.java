package com.lsy.common.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "bz_book")
public class Book extends AbstractAuditingEntity {
//    private static final long serialVersionUID = 1L;
    @NotBlank
    private String intro;

    @NotBlank
    private String name;

    @Min(value = 0)
    private Integer price;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
