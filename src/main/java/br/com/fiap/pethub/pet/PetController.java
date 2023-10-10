package br.com.fiap.pethub.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pet")
public class PetController {

	@Autowired
	PetService petService;

	@GetMapping
	public String index(Model model) {
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
}
