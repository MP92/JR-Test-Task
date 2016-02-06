package mypackage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import mypackage.model.User;
import mypackage.service.UserService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    UserService service;

    @Autowired
    MessageSource messageSource;


    @RequestMapping(value = { "/", "/users/list" }, method = RequestMethod.GET)
    public String listUsers(HttpServletRequest req, ModelMap model) {

        String nameFilter = req.getParameter("srch-name");
        if (nameFilter == null) {
            nameFilter = "";
        }

        List<User> users = nameFilter.isEmpty() ?
                            service.findAllUsers() :
                            service.findUsersByName(nameFilter);

        PagedListHolder<User> holder = new PagedListHolder<User>(users);
        int page = ServletRequestUtils.getIntParameter(req, "page", 0);
        holder.setPage(page);
        holder.setPageSize(5);

        model.addAttribute("pagedListHolder", holder);
        model.addAttribute("nameFilter", nameFilter);

        return "users/list";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") int id, Model model) {

        User user = service.findUserById(id);
        if (user == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "User not found");
        }
        model.addAttribute("user", user);

        return "users/show";
    }

    @RequestMapping(value = { "/users/new" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "users/registration";
    }

    @RequestMapping(value = { "/users/save" }, method = RequestMethod.POST)
    public String saveUser(HttpServletRequest req, @Valid User user, BindingResult result,
                               ModelMap model, final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "users/registration";
        }

        boolean isEdit = "true".equals(req.getParameter("edit"));

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", String.format("User %s successfully!", isEdit ? "updated" : "added"));

        if (isEdit) {
            service.updateUser(user);
        } else {
            service.saveUser(user);
        }

        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = { "/users/{id}/edit" }, method = RequestMethod.GET)
    public String editUser(@PathVariable int id, ModelMap model) {
        User user = service.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "users/registration";
    }

    @RequestMapping(value = { "/users/{id}/delete" }, method = RequestMethod.POST)
    public String deleteUser(HttpServletRequest req, @PathVariable int id, final RedirectAttributes redirectAttributes) {
        service.deleteUserById(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "User is deleted!");

        String url = "redirect:/users/list";

        String nameFilter = req.getParameter("srch-name");
        if (nameFilter != null && !nameFilter.isEmpty()) {
            url += ("?srch-name=" + nameFilter);
        }

        return url;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {

        ModelAndView model = new ModelAndView("error");
        model.addObject("exception", ex);

        return model;
    }
}
