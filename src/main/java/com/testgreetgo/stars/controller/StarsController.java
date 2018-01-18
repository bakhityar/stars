package com.testgreetgo.stars.controller;

import com.testgreetgo.stars.model.Discoverers;
import com.testgreetgo.stars.model.FlashMessage;
import com.testgreetgo.stars.model.Star;
import com.testgreetgo.stars.model.Color;
import com.testgreetgo.stars.service.DiscoverersService;
import com.testgreetgo.stars.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
public class StarsController {
  @Autowired
  private StarService starService;

  @Autowired
  private DiscoverersService discoverersService;

  //Домашняя страница. Список всех звезд
  @RequestMapping("/")
  @SuppressWarnings("unchecked")
  public String listStars(Model model) {
    List<Star> stars = starService.findAll();
    model.addAttribute("stars", stars);
    return "home";
  }

  //Одна выбранная звезда
  @RequestMapping("/star/{id}")
  public String starDetails(@PathVariable Long id, Model model) {
    Star star = starService.findById(id);
    model.addAttribute("star", star);
    return "star-details";
  }

  //Поиск звезды. Передается параметр из поля Поиск
  @RequestMapping("/search")
  public String searchStars (@RequestParam String q, Model model) {
    List<Star> stars = starService.searchByName(q);
    model.addAttribute("stars", stars);
    return "search";
  }

  //Добавление новой звезды. Если заполнено поле
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String addStar(@Valid Star star, BindingResult result, @RequestParam String discoverername, Model model, RedirectAttributes redirectAttributes) {
    model.addAttribute("discoverers", discoverersService.findAll());
    if (result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.star", result);
      redirectAttributes.addFlashAttribute("star", star);
      return "redirect:/add";
    }
    if(discoverername != null && !discoverername.isEmpty()) {
      Discoverers discoverers = new Discoverers();
      discoverers.setName(discoverername);
      discoverersService.save(discoverers);
      star.setDiscoverer(discoverers);
    }
    starService.save(star);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Звезда добавлена!", FlashMessage.Status.SUCCESS));
    return "redirect:/";
  }

  //Форма добавления новой звезды
  @RequestMapping("/add")
  public String formNewStar(Model model) {
    if (!model.containsAttribute("star")) {
      model.addAttribute("star", new Star());
    }
    model.addAttribute("colors", Color.values());
    model.addAttribute("discoverers", discoverersService.findAll());
    model.addAttribute("action", "/");
    model.addAttribute("heading", "Новая звезда");
    model.addAttribute("submit", "Добавить");
    return "form";
  }

  //Редактирование звезды
  @RequestMapping(value = "/star/{id}", method = RequestMethod.POST)
  public String updateStar(@Valid Star star, @RequestParam String discoverername, BindingResult result, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.star", result);
      redirectAttributes.addFlashAttribute("star", star);
      return String.format("redirect:/star/%s/edit", star.getId());
    }
    if(discoverername != null && !discoverername.isEmpty()) {
      //star.setDiscoverer(discoverername);
    }
    starService.save(star);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Звезда изменена!", FlashMessage.Status.SUCCESS));
    return "redirect:/";
  }

  //Форма редактирования звезды
  @RequestMapping("/star/{id}/edit")
  public String formEditStar(@PathVariable Long id, Model model) {
    if (!model.containsAttribute("star")) {
      model.addAttribute("star", starService.findById(id));
    }
    model.addAttribute("colors", Color.values());
    model.addAttribute("discoverers", discoverersService.findAll());
    model.addAttribute("action", String.format("/star/%s", id));
    model.addAttribute("heading", "Редактирование");
    model.addAttribute("submit", "Изменить");
    return "form";
  }

  //Удаление звезды
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

  //Форма авторизации
  @RequestMapping("/login")
  public String loginForm() {
    return "login";
  }


}
