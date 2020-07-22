package net.eegg.crudthymeleaf.controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.eegg.crudthymeleaf.entities.Juego;
import net.eegg.crudthymeleaf.repository.JuegoRepo;
import net.eegg.crudthymeleaf.service.PictureService;

@Controller
@RequestMapping("/Juegos")
public class JuegoControlador {
		@Autowired
		private JuegoRepo repo;
		
		@Autowired
		PictureService picService;
		
		@RequestMapping("")
		public String index() {
			return "index";
		}
		
		@GetMapping("/add_game")//
		public String showSignUpForm(Juego juego) {
			return "add_game";
		}
		
		@GetMapping("/list")
		public String showGames(Model model) {
			model.addAttribute("games", repo.findAll());
			return "list_games";
		}
		
		@RequestMapping("/login")
		public String showLogin() {
			return "login";
		}
		
		@GetMapping("/sc")
		public String showGames() {
			return "soundcloud.html";
		}
		
		@PreAuthorize("hasAuthority('admin')")
		@RequestMapping("/private")
		public String showPrivate(Model model) {
			model.addAttribute("games", repo.findAll());
			return "list_games";
		}
		
		@PreAuthorize("hasAuthority('admin')")
		@PostMapping("/add")
		public String addGame(Juego juego, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
			if (result.hasErrors()) {
				return "add_game";
			}
			UUID idPic = UUID.randomUUID();
			picService.uploadPicture(file, idPic);
			juego.setFoto(idPic);
			repo.save(juego);
			
			return "redirect:list";
			
		}
		@PreAuthorize("hasAuthority('admin')")
		@GetMapping("/edit/{id}")
		public String showUpdateForm(@PathVariable("id") Long id, Model model) {
			Juego juego = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
			model.addAttribute("game",juego);
			return "update_game";
		}
		
		@PreAuthorize("hasAuthority('admin')")
		@PostMapping("/update/{id}")
		public String updateGame(@PathVariable("id") Long id, Juego juego, BindingResult result, Model model, @RequestParam("file")  MultipartFile file) {
			if (result.hasErrors()) {
				juego.setId(id);
				return "update_game";
			}
			
			if(!file.isEmpty()) {
				picService.deletePicture(juego.getFoto());
				UUID idPic = UUID.randomUUID();
				picService.uploadPicture(file, idPic);
				juego.setFoto(idPic);
			}
			repo.save(juego);
			return "redirect:/juegos/list";
		}
		
		@PreAuthorize("hasAuthority('admin')")
		@GetMapping("/delete/{id}")
		public String deleteGame(@PathVariable("id") Long id, Model model) {
			Juego juego = repo.findById(id).orElseThrow(()  -> new IllegalArgumentException("Invalid game Id:" + id));
			repo.delete(juego);
			picService.deletePicture(juego.getFoto());
			model.addAttribute("GAMES", repo.findAll());
			return "list_games";
		}
		
}