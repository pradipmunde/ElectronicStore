package com.happytech.ElectronicStore.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntityClass {
    @Column(name="create_date", updatable =false)
    @CreationTimestamp
    private LocalDate createDate;

    @Column(name="update_date", insertable = false)
    @UpdateTimestamp
    private LocalDate updateDate;

    @Column(name="isactive_switch")
    private String isactive;
}
