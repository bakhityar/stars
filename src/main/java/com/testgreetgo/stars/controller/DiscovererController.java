package com.testgreetgo.stars.controller;

import com.testgreetgo.stars.model.Color;
import com.testgreetgo.stars.model.Discoverers;
import com.testgreetgo.stars.model.FlashMessage;
import com.testgreetgo.stars.service.DiscoverersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import javax.validation.Valid;

@Controller
public class DiscovererController {

  @Autowired
  private DiscoverersService discoverersService;

  //список всех открывателей
  @RequestMapping("/discoverers")
  @SuppressWarnings("unchecked")
  public String listDiscoverers(Model model) {
    List<Discoverers> discoverers = discoverersService.findAll();
    model.addAttribute("discoverers", discoverers);
    return "discoverers";
  }

  //один выбранный открыватель
  @RequestMapping("/discoverers/{id}")
  public String discovererDetails(@PathVariable Long id, Model model) {
    Discoverers discoverer = discoverersService.findById(id);
    model.addAttribute("discoverer", discoverer);
    return "discoverer-details";
  }

  //добавление нового открывателя
  @RequestMapping(value = "/discoverers", method = RequestMethod.POST)
  public String addDiscoverers(@Valid Discoverers discoverer, BindingResult result, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.discoverers", result);
      redirectAttributes.addFlashAttribute("discoverer", discoverer);
      return "redirect:/discoverers/add";
    }
    discoverersService.save(discoverer);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Открыватель добавлен!", FlashMessage.Status.SUCCESS));
    return "redirect:/discoverers";
  }

  //форма добавления нового открывателя
  @RequestMapping("discoverers/add")
  public String formNewDiscoverer(Model model) {
    if (!model.containsAttribute("discoverer")) {
      model.addAttribute("discoverer", new Discoverers());
    }
    model.addAttribute("action", "/discoverers");
    model.addAttribute("heading", "Новый открыватель");
    model.addAttribute("submit", "Добавить");
    return "form-discoverers";
  }

  //редактировать открывателя
  @RequestMapping(value = "/discoverers/{id}", method = RequestMethod.POST)
  public String updateDiscoverer(@Valid Discoverers discoverer, BindingResult result, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.discoverers", result);
      redirectAttributes.addFlashAttribute("discoverer", discoverer);
      return String.format("redirect:/discoverers/%s/edit", discoverer.getId());
    }
    discoverersService.save(discoverer);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Открыватель изменен!", FlashMessage.Status.SUCCESS));
    return "redirect:/discoverers";
  }

  //форма для редактирования. Передается id открывателя
  @RequestMapping("/discoverers/{id}/edit")
  public String formEditDiscoverer(@PathVariable Long id, Model model) {
    if (!model.containsAttribute("discoverer")) {
      model.addAttribute("discoverer", discoverersService.findById(id));
    }
    model.addAttribute("action", String.format("/discoverers/%s", id));
    model.addAttribute("heading", "Редактирование");
    model.addAttribute("submit", "Изменить");
    return "form-discoverers";
  }

  //Удаление открывателя
  @RequestMapping(value = "/discoverers/{id}/delete", method = RequestMethod.POST)
  public String deleteDisciverer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    Discoverers discoverer = discoverersService.findById(id);
    discoverersService.delete(discoverer);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Открыватель удален!", FlashMessage.Status.SUCCESS));
    return "redirect:/discoverers";
  }
}
