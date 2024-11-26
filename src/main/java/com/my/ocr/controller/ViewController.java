package com.my.ocr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

  @GetMapping(value = {"/", "/main", "/index"})
  public String index(){
    return "index";
  }
}
