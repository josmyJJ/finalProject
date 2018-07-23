package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity // We declare a class as an @Entity when there is a primary key/id for each object
public class Pet {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    // id is a unique identifier for each submission from the user
    // The value for id is auto-generated (specified by strategy=GenerationType.AUTO)

    @NotNull
    private String content;

    private String petFound;

    private Date posteddate;

    private String datePosted;

    private String postImg;

    private String hash;

    @ManyToOne(fetch = FetchType.EAGER)
    //@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Date getPosteddate() {
        return posteddate;
    }

    // Modified the original setPosteddate() method to return
    // the current date
    public void setPosteddate() {
        posteddate = new Date(); // This creates a new Date object with the current date
        this.posteddate = posteddate;
    }

    public String getPostImg() {
        return postImg;
    }

    public void setPostImg(String postImg) {
        this.postImg = postImg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getPetFound() {
        return petFound;
    }

    public void setPetFound(String petFound) {
        this.petFound = petFound;
    }
}
