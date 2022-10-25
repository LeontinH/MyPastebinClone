package MyPastebinClone.controller;

import MyPastebinClone.model.PasteModel;
import MyPastebinClone.repository.PasteRepository;
import MyPastebinClone.service.PasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PasteController {

    @Autowired
    private PasteRepository pasteRepository;

    @Autowired
    private final PasteService pasteService;

    public PasteController(PasteService pasteService) { this.pasteService = pasteService; }

    @PostMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/addPaste")
    public String addPaste (Model model) {
        model.addAttribute("pastebin", new PasteModel());
        return "addNewPastePage";
    }

    @GetMapping("/pasteList")
    public String pasteListPage (Model model) {
        List<PasteModel> pasteList = pasteService.listAllPaste();
        model.addAttribute("pasteList", pasteList);
        return "pastePage";
    }

    @PostMapping(value = "/savePaste")
    public String savePaste (@ModelAttribute("pastebin") PasteModel paste) {
        pasteService.save(paste);
        return "redirect:/pasteList";
    }

    @RequestMapping("/editPaste/{paste_id}")
    public String editPaste (@PathVariable(value = "paste_id") long paste_id, Model model) {
        model.addAttribute("pastebin", pasteService.getPasteById(paste_id));
        return "editPastePage";
    }

    @RequestMapping("/deletePaste/{paste_id}")
    public String deletePaste (@PathVariable(name = "paste_id") long paste_id) {
        pasteService.deleteById(paste_id);
        return "redirect:/pasteList";
    }
}
