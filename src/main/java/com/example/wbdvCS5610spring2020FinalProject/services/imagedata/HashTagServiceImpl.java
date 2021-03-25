package com.example.wbdvCS5610spring2020FinalProject.services.imagedata;

import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.HashTag;
import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.Image;
import com.example.wbdvCS5610spring2020FinalProject.models.imagedata.ImageFromHashTag;
import com.example.wbdvCS5610spring2020FinalProject.repositories.HashTagRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * This class is an implementation of the {@link IHashTagService} it provides the functionality to
 * add hash tags and retrieve hashTags
 */
@Service
public class HashTagServiceImpl implements IHashTagService {

  @Autowired
  HashTagRepository hashTagRepository;

  @Override
  public List<HashTag> findAllHashTags() {
    return (List<HashTag>) hashTagRepository.findAll();
  }

  @Override
  public ResponseEntity<HashTag> createHashTag(HashTag hashTag) {
//    HashTag existingHashTag = hashTagRepository.findHashTag(hashTag.getHashTagText());
//
//    if (existingHashTag == null) {
//      HashTag saved = hashTagRepository.save(hashTag);
//      return new ResponseEntity<>(saved, HttpStatus.ACCEPTED);
//    }
    HashTag createdHashTag = hashTagRepository.save(hashTag);

    return new ResponseEntity<>(createdHashTag, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<List<HashTag>> getAllHashTagsStartingWith(String hashTag) {
    List<HashTag> hashTagList = hashTagRepository.findHashTags(hashTag);
    List<HashTag> resultList = new ArrayList<>();

    if (hashTagList.size() == 0) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    int size = Math.min(hashTagList.size(), 10);

    Set<String> hashTagSet = new HashSet<>();

    for (int i = 0; i < size; i++) {
      if(hashTagSet.contains(hashTagList.get(i).getHashTagText())) {
        continue;
      }
      resultList.add(hashTagList.get(i));
      hashTagSet.add(hashTagList.get(i).getHashTagText());
    }

    return new ResponseEntity<>(resultList, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<HashTag> getHashTag(String hashTagText) {
    HashTag hashTag = hashTagRepository.findHashTag(hashTagText);

    if (hashTag == null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(hashTag, HttpStatus.ACCEPTED);
  }

  @Override
  public void save(HashTag hashTag) {
    hashTagRepository.save(hashTag);
  }
}
