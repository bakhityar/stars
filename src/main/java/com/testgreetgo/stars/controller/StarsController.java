package com.testgreetgo.stars.controller;

import com.testgreetgo.stars.data.StarRepository;
import com.testgreetgo.stars.model.Star;
import com.testgreetgo.stars.model.Color;
import com.testgreetgo.stars.model.Discoverer;
import com.testgreetgo.stars.service.StarService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class StarsController {
  @Autowired
  private StarService starService;

  @Autowired
  private StarRepository starRepository;

  @RequestMapping("/")
  @SuppressWarnings("unchecked")
  public String listStars(Model model) {
    List<Star> stars = starService.findAll();
    model.addAttribute("stars", stars);
    return "home";
  }

  @RequestMapping("/star/{id}")
  public String starDetails(@PathVariable Long id, Model model) {
    Star star = starService.findById(id);
    model.addAttribute("star", star);
    return "star-details";
  }

  @RequestMapping("/search")
  public String searchStars (@RequestParam String q, Model model) {
    List<Star> stars = starService.searchByName(q);
    model.addAttribute("stars", stars);
    return "search";
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String addStar(Star star) {
    starService.save(star);
    return "redirect:/";
  }

  @RequestMapping("/add")
  public String formNewStar(Model model) {
    model.addAttribute("star", new Star());
    model.addAttribute("colors", Color.values());
    model.addAttribute("discoverers", Discoverer.values());
    return "form";
  }
}
