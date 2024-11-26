package com.my.ocr.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class OcrService {

  // 글자 추출
  public String extractText(MultipartFile file) throws Exception {
    Tesseract tesseract = new Tesseract();
    tesseract.setDatapath("D:\\OCR\\tessdata"); // tessdata 경로 설정
    tesseract.setLanguage("kor+eng"); // 다중 언어 순서 변경
    tesseract.setPageSegMode(6); // 6: 단락분석, 3: 완전한 페이지 분석, 1: 자동 레이아웃 감지

    File convFile = new File("D:\\OCR\\temp\\" + file.getOriginalFilename());
    file.transferTo(convFile);

    try {
      return tesseract.doOCR(convFile);
    } catch (TesseractException e) {
      throw new Exception("OCR Processing Failed", e);
    } finally {
      if (convFile.exists()) {
        convFile.delete();
      }
    }
  }



}
