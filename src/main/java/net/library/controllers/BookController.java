package net.library.controllers;

import net.library.dao.BookDAO;
import net.library.dao.BookDAOImpl;
import net.library.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDAO bookDAO = new BookDAOImpl();

    @GetMapping()
    public String books(Model model){
        List<Book> books = bookDAO.allBook();
        model.addAttribute("books", books);
        model.addAttribute("book", new Book());
        return "book";
    }

    @GetMapping("/add")
    public String addPage(Model model){
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("add")
    public String addBook(@ModelAttribute("book") @Valid Book book,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "addBook";

        bookDAO.addBook(book);
        return "redirect:/book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookDAO.deleteBook(id);
        return "redirect:/book";
    }

    @GetMapping("/update/{id}")
    public String updatePage(Model model, @PathVariable("id") Long id){
        model.addAttribute("book", bookDAO.readBookById(id));
        return "updateBook";
    }

    @PostMapping("update/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult, @PathVariable("id") Long id){
        if(bindingResult.hasErrors())
            return "updateBook";

        bookDAO.updateBook(id, book);
        return "redirect:/book";
    }

}
