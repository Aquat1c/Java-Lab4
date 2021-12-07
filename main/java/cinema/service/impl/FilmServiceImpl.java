package cinema.service.impl;

import cinema.domain.Film;
import org.springframework.stereotype.Service;
import cinema.repository.FilmRepository;
import cinema.service.FilmService;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public void createFilm(Film film) {
        film.setId(null);
        filmRepository.save(film);
    }

    @Override
    public Optional<Film> findFilmById(Long id) {
        return filmRepository.findById(id);
    }

}
