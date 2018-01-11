package com.testgreetgo.stars.controller;

import com.testgreetgo.stars.data.StarRepository;
import com.testgreetgo.stars.model.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StarsController {
  @Autowired
  private StarRepository starRepository;

  @RequestMapping("/")
  public String listStars(Model model) {
    model.addAttribute("stars", starRepository.allStars());
    return "home";
  }

  @RequestMapping("/star/{name}")
  public String starDetails(@PathVariable String name, Model model) {
    Star star = starRepository.findByName(name);
    model.addAttribute("star", star);
    return "star-details";
  }

  @RequestMapping("/search")
  public String searchStars (@RequestParam String q, Model model) {
    List<Star> stars = starRepository.searchByName(q);
    model.addAttribute("stars", stars);
    return "search";
  }
}
