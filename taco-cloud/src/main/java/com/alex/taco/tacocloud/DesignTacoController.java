package com.alex.taco.tacocloud;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
  	@GetMapping
  	public String showDesignForm(Model model) {
  		List<Ingredient> ingredients = Arrays.asList(
	  		new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
	  		new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
	  		new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
	  		new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
	  		new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
	  		new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
	  		new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
	  		new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
	  		new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
	  		new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
		);
	
		Ingredient.Type[] types = Ingredient.Type.values();
		for (Ingredient.Type type : types) {
	  		model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		model.addAttribute("design", new Taco());
    	return "design";
  	}

  	@PostMapping
  	public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {
    	if (errors.hasErrors()) {
      		return "design";
    	}
    	// Save the taco design...
    	// We'll do this in chapter 3
    	log.info("Processing design: " + design);
	    return "redirect:/orders/current";
  	}

  	private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
  	}
}




