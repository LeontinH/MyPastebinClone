package MyPastebinClone.controller;

import MyPastebinClone.model.PasteModel;
import MyPastebinClone.repository.PasteRepository;
import MyPastebinClone.service.PasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class PasteController {

    @Autowired
    private PasteRepository pasteRepository;

    @Autowired
    private final PasteService pasteService;

    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService; }

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
