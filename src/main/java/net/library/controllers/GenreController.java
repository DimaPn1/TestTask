package net.library.controllers;

import net.library.dao.GenreDAO;
import net.library.dao.GenreDAOImpl;
import net.library.models.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/genre")
public class GenreController {

    private final GenreDAO genreDAO = new GenreDAOImpl();

    @GetMapping()
    public String genres(Model model){
        List<Genre> genres = genreDAO.allGenre();
        model.addAttribute("genres", genres);
        return "genre";
    }

    @GetMapping("/add")
    public String addPage(Model model){
        model.addAttribute("genre", new Genre());
        return "addGenre";
    }

    @PostMapping("add")
    public String addGenre(@ModelAttribute("genre") @Valid Genre genre,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "addGenre";

        genreDAO.addGenre(genre);
        return "redirect:/genre";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable("id") Long id) {
        genreDAO.deleteGenre(id);
        return "redirect:/genre";
    }

    @GetMapping("/update/{id}")
    public String updatePage(Model model, @PathVariable("id") Long id){
        model.addAttribute("genre", genreDAO.readGenreById(id));
        return "updateGenre";
    }

    @PostMapping("update/{id}")
    public String updateGenre(@ModelAttribute("genre") @Valid Genre genre,
                              BindingResult bindingResult, @PathVariable("id") Long id){
        if(bindingResult.hasErrors())
            return "updateGenre";

        genreDAO.updateGenre(id, genre);
        return "redirect:/genre";
    }

    @GetMapping("/stat/{id}")
    public String genreStat(Model model, @PathVariable("id") Long id){
        int quantity = genreDAO.bookStatistics(id);
        model.addAttribute("quantity", quantity);
        model.addAttribute("genre", genreDAO.readGenreById(id));
        return "genreStat";
    }
}
