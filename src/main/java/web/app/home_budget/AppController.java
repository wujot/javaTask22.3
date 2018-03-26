package web.app.home_budget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    private HomeBudgetDao dao;

    public AppController(HomeBudgetDao dao) {
        dao = new HomeBudgetDao();
        this.dao = dao;
    }

    // display homepage
    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("homeBudget", new HomeBudget());
        return "index";
    }

    // add home budget
    @PostMapping("/addHomeBudget")
    public String addProduct(@ModelAttribute HomeBudget homeBudget, Model model) {
        model.addAttribute("homeBudget", homeBudget);
        LocalDate date = LocalDate.now();
        homeBudget.setDate(Date.valueOf(date));
        dao.save(homeBudget);
        return "redirect:/";
    }

    // display by home budget type
    @GetMapping("/display")
    public String display(@RequestParam String type, Model model) {
        String tableTitle;
        List<HomeBudget> homeBudgets;
        if (type.equals("Income")) {
            tableTitle = "Incomes";
        }else {
            tableTitle = "Expenses";
        }
        model.addAttribute("tableTitle", tableTitle);
        homeBudgets = dao.read(type);
        model.addAttribute("homeBudgets", homeBudgets);
        return "display";
    }
}
