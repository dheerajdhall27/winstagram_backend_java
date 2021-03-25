package com.example.wbdvCS5610spring2020FinalProject.services.imagedata;

import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.HashTag;
import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.Image;
import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.ImageFromHashTag;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * This interface represents the HashTag service which provides the functionality to add hash tags
 * and search for hash tags.
 */
public interface IHashTagService {

  /**
   * This method is to list out all the hash tags that were saved in the data base
   *
   * @return the List of hash tags that were saved.
   */
  List<HashTag> findAllHashTags();

  /**
   * This method is used to create a new hash tag. If the hash tag exists it returns the existing
   * Hash Tag
   *
   * @param hashTag the new hash tag that is being created
   * @return the Hash tag object after creation
   */
  ResponseEntity<HashTag> createHashTag(HashTag hashTag);

  /**
   * This method is to get all the Hash Tags that start with the provided string
   *
   * @param hashTag the string which is referred to look for the hash tags in the DataBase.
   * @return the List of hash tags that start with the string provided otherwise Bad Request.
   */
  ResponseEntity<List<HashTag>> getAllHashTagsStartingWith(String hashTag);

  /**
   * This method is used to get an individual hash tag using the string provided
   *
   * @param hashTag the hash tag that needs to be searched
   * @return the Hash Tag object if found otherwise a Bad Request
   */
  ResponseEntity<HashTag> getHashTag(String hashTag);

  /**
   * This method is used to save the hash tag provided
   * @param hashTag the hash tag that needs to be saved
   */
  void save(HashTag hashTag);
}
