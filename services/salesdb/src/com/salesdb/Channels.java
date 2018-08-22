/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.salesdb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Channels generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`CHANNELS`")
public class Channels implements Serializable {

    private Integer id;
    private String name;
    private List<Reps> repses;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`NAME`", nullable = false, length = 40)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "channels")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
    public List<Reps> getRepses() {
        return this.repses;
    }

    public void setRepses(List<Reps> repses) {
        this.repses = repses;
    }

    @PostPersist
    public void onPostPersist() {
        if(repses != null) {
            repses.forEach(reps -> reps.setChannels(this));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channels)) return false;
        final Channels channels = (Channels) o;
        return Objects.equals(getId(), channels.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

