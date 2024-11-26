package com.my.ocr.controller;

import com.my.ocr.service.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ApiController {

  private final OcrService ocrService;

  @PostMapping("/upload")
  public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("image") MultipartFile image){
    try{
      return ResponseEntity.status(HttpStatus.OK).body(Map.of("text", ocrService.extractText(image)));
    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Failed to process image."));
    }
  }

}
