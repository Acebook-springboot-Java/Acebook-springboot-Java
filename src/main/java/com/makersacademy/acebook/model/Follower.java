package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
  
@Data
@Entity
@Table(name = "FOLLOWER")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long followerId;
    private Boolean pending;
    private Boolean accepted;

    public Follower() {}

    public Follower(Long id, Long userId, Long followerId, Boolean pending, Boolean accepted) {
        this.id = id;
        this.userId = userId;
        this.followerId = followerId;
        this.pending = true;
        this.accepted = false;
    }

    public Long getId() {
      return this.id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Long getFollowerId() {
        return this.followerId;
    }

    public boolean getPending() {
        return pending;
    }

    public boolean getAccepted() {
      return accepted;
    }

    public void setPending(Long id) {
        this.pending = true;
    }

    public void setAccepted(Long id) {
      this.accepted = true;
   }
}
