package net.library.controllers;


import net.library.dao.AuthorDAO;
import net.library.dao.AuthorDAOImpl;
import net.library.models.Author;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorDAO authorDAO = new AuthorDAOImpl();

    @GetMapping()
    public String authors(Model model){
        List<Author> authors = authorDAO.allAuthor();
        model.addAttribute("authors", authors);
        return "author";
    }

    @GetMapping("/add")
    public String addPage(Model model){
        model.addAttribute("author", new Author());
        return "addAuthor";
    }

    @PostMapping("add")
    public String addAuthor(@ModelAttribute("author") @Valid Author author,
                            BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "addAuthor";

        authorDAO.addAuthor(author);
        return "redirect:/author";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Long id){
        authorDAO.deleteAuthor(id);
        return "redirect:/author";
    }

    @GetMapping("/update/{id}")
    public String updatePage(Model model, @PathVariable("id") Long id){
        model.addAttribute("author", authorDAO.readAuthorById(id));
        return "updateAuthor";
    }

    @PostMapping("update/{id}")
    public String updateAuthor(@ModelAttribute("author") @Valid Author author,
                               BindingResult bindingResult, @PathVariable("id") Long id){
        if(bindingResult.hasErrors())
            return "updateAuthor";

        authorDAO.updateAuthor(id, author);
        return "redirect:/author";
    }
}
