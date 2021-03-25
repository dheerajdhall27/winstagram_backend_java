package com.example.wbdvCS5610spring2020FinalProject.models.imagedata;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * An HashTag class to create the hashTag table and to map image
 * associated with an hashTag.
 */

@Entity
@Table(name="Hashtags")
public class HashTag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String hashTagText;

  @ManyToOne
  @JsonIgnore
  private Image image;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getHashTagText() {
    return hashTagText;
  }

  public void setHashTagText(String hashTagText) {
    this.hashTagText = hashTagText;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }
}
