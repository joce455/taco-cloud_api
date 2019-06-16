package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import tacos.models.Ingredient;
import tacos.models.Order;
import tacos.models.Taco;
import tacos.models.Ingredient.Type;
import tacos.repository.IngredientRepository;
import tacos.repository.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepo;
	private TacoRepository designRepo;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
	}

	@ModelAttribute(name = "order")
	public Order order() {
	return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
	return new Taco();
	}
	
	@ModelAttribute(name = "Ingredients")
	public void Ingredients(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}
	
	
	@GetMapping
	public String showDesignForm(Model model) {

		
		return "design";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}

	@PostMapping
	public String processDesign(@Valid Taco taco,Errors errors,@ModelAttribute Order order ) {
		if (errors.hasErrors()) {
			return "design";
		}
		log.info("Processing design: " + taco);
		Taco saved = designRepo.save(taco);
		order.addDesign(saved);
		
		return "redirect:/orders/current";
	}
}
