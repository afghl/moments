package com.moments.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.moments.serializers.CommentSerializer;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // return moment_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moment_id", foreignKey = @javax.persistence.ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
//    @JsonBackReference
    @JsonSerialize(using = CommentSerializer.class)
    private Moment moment;

    private String body;

    private Long type;

    private Long userId;

    private String userName;

    private String userAvatar;

    private Long otherId;

    private String otherName;

    private String otherAvatar;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    private void preInsert() {
        if (createdAt == null) createdAt = new Date();
        if (updatedAt == null) updatedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Moment getMoment() {
        return moment;
    }

    public void setMoment(Moment moment) {
        this.moment = moment;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Long getOtherId() {
        return otherId;
    }

    public void setOtherId(Long otherId) {
        this.otherId = otherId;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getOtherAvatar() {
        return otherAvatar;
    }

    public void setOtherAvatar(String otherAvatar) {
        this.otherAvatar = otherAvatar;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
