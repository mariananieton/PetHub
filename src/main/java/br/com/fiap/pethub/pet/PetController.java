package br.com.fiap.pethub.pet;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pet")
public class PetController {

	@Autowired
	PetService petService;

	@GetMapping
	public String index(Model model, @AuthenticationPrincipal OAuth2User user) {
		model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
		model.addAttribute("username", user.getAttribute("name"));
		model.addAttribute("pets", petService.findAll());
		return "pet/index";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirect) {
		if (petService.delete(id)){
			redirect.addFlashAttribute("success", "Pet excluído com sucesso!");
		} else {
			redirect.addFlashAttribute("error", "Pet não encontrado!");
		}
		return "redirect:/pet";
	}

	@GetMapping("new")
	public String form(Model model) {
		model.addAttribute("pet", new Pet());
		return "pet/form";
	}

	@PostMapping
	public String save(@Valid Pet pet, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) return "/pet/form";

		petService.save(pet);
		redirect.addFlashAttribute("success", "Pet cadastrado com sucesso!");
		return "redirect:/pet";
	}
}
