package cinema.service;

import cinema.domain.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {

    List<Film> getAllFilms();

    void createFilm(Film film);

    Optional<Film> findFilmById(Long id);

}
