package com.example.wbdvCS5610spring2020FinalProject.controllers;

import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.HashTag;
import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.ImageFromHashTag;

import com.example.wbdvCS5610spring2020FinalProject.services.imagedata.IHashTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class represents a controller for the HashTag table It provides the endpoints to call the
 * API to find all the HashTags and to find all the images associated with a HashTag.
 */

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class HashTagController {

  @Autowired
  IHashTagService hashTagService;

  @GetMapping("/api/hashtags")
  public List<HashTag> findAllHashTags() {
    return hashTagService.findAllHashTags();
  }


  @GetMapping("/api/search/hashtag/{searchString}")
  public ResponseEntity<HashTag> searchHashTag(@PathVariable("searchString") String searchString) {
    return hashTagService.getHashTag(searchString);
  }

  @GetMapping("/api/search/hashtags/{searchString}")
  public ResponseEntity<List<HashTag>> searchHashTags(
      @PathVariable("searchString") String searchString) {
    return hashTagService.getAllHashTagsStartingWith(searchString);
  }
}
