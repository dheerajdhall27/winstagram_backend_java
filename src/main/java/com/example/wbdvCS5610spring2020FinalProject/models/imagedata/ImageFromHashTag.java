package com.example.wbdvCS5610spring2020FinalProject.models.imagedata;

/**
 * An interface to create a virtual table which holds details from Image and
 * HashTag table.
 */
public interface ImageFromHashTag {

  String getImgURL();

  String getHashTagText();

  Integer getid();
}
