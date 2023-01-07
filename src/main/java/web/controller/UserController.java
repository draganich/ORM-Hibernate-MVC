package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImpl;

@Controller
public class UserController {
	private final UserServiceImpl userService;

	@Autowired
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@Transactional
	@GetMapping("/users")
	public String getUsers(@RequestParam(value = "num", defaultValue = "10") int num, ModelMap model) {
		model.addAttribute("users", userService.userList(num));
		return "/users";
	}

	@Transactional
	@GetMapping("/users/create")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "/create";
	}

	@Transactional
	@PostMapping("/users")
	public String createUser(@ModelAttribute("user") User user) {
		userService.save(user);
		return "redirect:/users";
	}

	@Transactional
	@GetMapping("/users/{id}/update")
	public String updateUser(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userService.getID(id));
		return "/update";
	}

	@Transactional
	@PatchMapping("/users/{id}")
	public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
		userService.update(id, user);
		return "redirect:/users";
	}

	@Transactional
	@DeleteMapping("/users/{id}")
	public String delete(@PathVariable("id") int id) {
		userService.delete(id);
		return "redirect:/users";
	}

}