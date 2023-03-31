package com.happytech.ElectronicStore.dtos;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDate;

public class BaseDtoClass {
    @Column(name="create_date", updatable =false)
    @CreationTimestamp
    private LocalDate createDate;

    @Column(name="update_date", insertable = false)
    @UpdateTimestamp
    private LocalDate updateDate;

    @Column(name="isactive_switch")
    private String isactive;
}
