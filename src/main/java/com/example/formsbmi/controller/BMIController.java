package com.example.formsbmi.controller;

import com.example.formsbmi.service.BMIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BMIController {

    private final BMIService bmiService;

    @Autowired
    public BMIController(BMIService bmiService) {
        this.bmiService = bmiService;
    }

    @GetMapping("/")
    public String Forside() {
        return "Forside";
    }

    @PostMapping("/UdregnBMI")
    public String UdregnerMetode(@RequestParam("højde") double højde, @RequestParam("vægt") double vægt, RedirectAttributes redirectAttributes) {
        double bmi = bmiService.udregnBMI(højde, vægt);

        redirectAttributes.addAttribute("bmi", bmi);
        redirectAttributes.addAttribute("højde", højde);
        redirectAttributes.addAttribute("vægt", vægt);
        return "redirect:/VisResultat";
    }

    @GetMapping("/VisResultat")
    public String visResultat(@RequestParam("bmi") double bmi, @RequestParam("højde") double højde, @RequestParam("vægt") double vægt, Model model) {
        model.addAttribute("bmi", bmi);
        model.addAttribute("højde", højde); // Skal anvende den modtagne højde
        model.addAttribute("vægt", vægt); // Skal anvende den modtagne vægt
        return "VisResultat";
    }
}
