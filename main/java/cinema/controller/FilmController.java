package cinema.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cinema.domain.Film;
import cinema.service.FilmService;

@Controller
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/get-films")
    public String getGetFilms(Model model) {
        model.addAttribute("films", filmService.getAllFilms());
        return "get-films";
    }

    @GetMapping("/add-film")
    public String getAddFilm() {
        return "add-film";
    }

    @PostMapping("/add-film")
    public String postAddFilm(@RequestParam String name, @RequestParam int minutes, @RequestParam int week) {
        Film film = new Film();
        film.setName(name);
        film.setMinutes(minutes);
        film.setWeek(week);
        try {
            filmService.createFilm(film);
            return "redirect:/get-films";
        } catch (DataIntegrityViolationException e) {
            return "redirect:/add-film?error";
        }
    }

}
