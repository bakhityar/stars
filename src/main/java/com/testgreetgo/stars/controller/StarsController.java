package com.testgreetgo.stars.controller;

import com.testgreetgo.stars.data.StarRepository;
import com.testgreetgo.stars.model.Discoverers;
import com.testgreetgo.stars.model.FlashMessage;
import com.testgreetgo.stars.model.Star;
import com.testgreetgo.stars.model.Color;
import com.testgreetgo.stars.model.Discoverer;
import com.testgreetgo.stars.service.StarService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
  public String addStar(@Valid Star star, BindingResult result, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.star", result);
      redirectAttributes.addFlashAttribute("star", star);
      return "redirect:/add";
    }
    starService.save(star);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Звезда добавлена!", FlashMessage.Status.SUCCESS));
    return "redirect:/";
  }

  @RequestMapping("/add")
  public String formNewStar(Model model) {
    if (!model.containsAttribute("star")) {
      model.addAttribute("star", new Star());
    }
    model.addAttribute("colors", Color.values());
    List<String> discs = new Discoverers().getDiscoverers();
    model.addAttribute("discoverers", discs);
    model.addAttribute("action", "/");
    model.addAttribute("heading", "Новая звезда");
    model.addAttribute("submit", "Добавить");
    return "form";
  }

  @RequestMapping(value = "/star/{id}", method = RequestMethod.POST)
  public String updateStar(@Valid Star star, BindingResult result, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.star", result);
      redirectAttributes.addFlashAttribute("star", star);
      return String.format("redirect:/star/%s/edit", star.getId());
    }
    starService.save(star);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Звезда изменена!", FlashMessage.Status.SUCCESS));
    return "redirect:/";
  }

  @RequestMapping("/star/{id}/edit")
  public String formEditStar(@PathVariable Long id, Model model) {
    if (!model.containsAttribute("star")) {
      model.addAttribute("star", starService.findById(id));
    }
    model.addAttribute("colors", Color.values());
    List<String> discs = new Discoverers().getDiscoverers();
    model.addAttribute("discoverers", discs);
    model.addAttribute("action", String.format("/star/%s", id));
    model.addAttribute("heading", "Редактирование");
    model.addAttribute("submit", "Изменить");
    return "form";
  }

  @RequestMapping(value = "/star/{id}/delete", method = RequestMethod.POST)
  public String deleteStar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    Star star = starService.findById(id);
    if (star.getColor().equals("yellow")) {
      redirectAttributes.addFlashAttribute("flash", new FlashMessage("Желтую звезду удалять нельзя!", FlashMessage.Status.FAILURE));
      return "redirect:/";
    }
    starService.delete(star);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Звезда удалена!", FlashMessage.Status.SUCCESS));
    return "redirect:/";
  }

  @RequestMapping("/login")
  public String loginForm() {
    return "login";
  }

}
