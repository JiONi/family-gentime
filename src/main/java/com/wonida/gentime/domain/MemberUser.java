package com.wonida.gentime.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="USER")
public class MemberUser {

    @Id
    private String userId;

    @Column
    private String userName;

    @Column
    private Long accessCount;

}
