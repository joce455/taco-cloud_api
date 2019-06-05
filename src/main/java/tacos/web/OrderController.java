package tacos.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.models.Order;
import tacos.models.User;
import tacos.repository.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private OrderRepository orderRepo;

	@Autowired
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	@GetMapping("/current")
	public String orderForm(Model model) {

		return "orders/orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
			Authentication authentication) {
		if (errors.hasErrors()) {
			return "orders/orderForm";
		}
		User user = (User) authentication.getPrincipal();
		order.setUser(user);
		orderRepo.save(order);

		sessionStatus.setComplete();
		return "redirect:/";
	}
	@GetMapping
	public String ordersForUser(
	@AuthenticationPrincipal User user, Model model) {
		PageRequest page = PageRequest.of(
				0, 12, Sort.by("createdAt").descending());
	model.addAttribute("orders",
	orderRepo.findByUserOrderByPlacedAtDesc(user,page));
	return "orderList";
	}

}
