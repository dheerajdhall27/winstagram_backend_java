package com.example.wbdvCS5610spring2020FinalProject.models.imagedata;

import com.example.wbdvCS5610spring2020FinalProject.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * An image class to crete the image table and to map a list of HashTags associated with an image.
 */

@Entity
@Table(name = "Images")
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int _id;
  private String imageUrl;
  private int price;
  private String userHandle;

  @ManyToOne
  @JsonIgnore
  private User user;

  @OneToMany(mappedBy = "image")
  private List<HashTag> hashTags;

  public int get_id() {
    return _id;
  }

  public void set_id(int id) {
    this._id = id;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imgName) {
    this.imageUrl = imgName;
  }

  public List<HashTag> getHashTags() {
    return hashTags;
  }

  public void setHashTags(List<HashTag> hashTags) {
    this.hashTags = hashTags;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getUserHandle() {
    return userHandle;
  }

  public void setUserHandle(String userHandle) {
    this.userHandle = userHandle;
  }
}
