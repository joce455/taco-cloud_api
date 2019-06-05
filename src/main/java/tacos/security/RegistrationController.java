package tacos.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	private UserRepository userRepo;
	private Encodeador passwordEncoder;
	
	@Autowired
	public RegistrationController(UserRepository userRepo, Encodeador passwordEncoder) {
	this.userRepo = userRepo;
	this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping
	public String registerForm() {
	return "registration";
	}
	
	@PostMapping
	public String processRegistration(RegistrationForm form) {
	userRepo.save(form.toUser(passwordEncoder));
	return "redirect:/login";
	}
}
