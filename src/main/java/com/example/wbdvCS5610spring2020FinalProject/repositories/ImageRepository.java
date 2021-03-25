package com.example.wbdvCS5610spring2020FinalProject.repositories;

import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.Image;

import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.ImageFromHashTag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * An interface which extends CrudRepository for basic CRUD operations and also has methods to fetch
 * data using native query from image table.
 */
public interface ImageRepository extends CrudRepository<Image, Integer> {

  @Query(value = "Select image_url as imgURL, hash_tag_text as hashtagtext, _id as id " +
      "from images, hashtags\n" +
      "where images._id = hashtags.image__id\n" +
      "and hashtags.hash_tag_text =:HashTag", nativeQuery =
      true)
  List<ImageFromHashTag> findAllImagesWithAHashTag(@Param("HashTag") String HashTag);

  @Query(value = "SELECT * FROM images WHERE image_url=:imgUrl", nativeQuery = true)
  Image findImageByUrl(@Param("imgUrl") String imgUrl);


//  @Query(value = "Select * from images where id=:id", nativeQuery = true)
//  Image deleteImage(@Param("id") Integer id);
}
