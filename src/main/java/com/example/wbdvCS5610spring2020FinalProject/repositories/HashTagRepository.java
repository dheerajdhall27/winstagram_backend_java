package com.example.wbdvCS5610spring2020FinalProject.repositories;

import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.HashTag;
import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.ImageFromHashTag;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * An interface which extends CrudRepository for basic CRUD operations and also has methods to fetch
 * data using native query from hashTag table.
 */
public interface HashTagRepository extends CrudRepository<HashTag, Integer> {

  @Query(value = "SELECT * FROM hashtags WHERE hash_tag_text like %:hashTagText% ",
  nativeQuery = true)
  List<HashTag> findHashTags(@Param("hashTagText") String hashTagText);

  @Query(value = "SELECT * FROM hashtags WHERE hash_tag_text=:hashTag", nativeQuery = true)
  HashTag findHashTag(@Param("hashTag") String hashTag);

  @Modifying
  @Transactional
  @Query(value ="DELETE FROM hashtags where image__id=:id", nativeQuery = true)
  void deleteImageById(@PathVariable("id")Integer id);
}
