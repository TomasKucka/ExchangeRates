package lt.lb.exchangerates;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExchangeRatesController {

    private static String errorMessage = "";
    private static List<ExchangeRatesWrapper> exchangeRates = new ArrayList<>();

    private String exchangeRatesDate = "";

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap modelMap, Model model) {
        modelMap.addAttribute("exchangeRates", exchangeRates);
        
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("exchangeRatesDate", exchangeRatesDate);

        return "index";
    }

    @RequestMapping(value = "/showExchangeRatesByDate", method = RequestMethod.POST)
    public String showExchangeRatesByDate(@ModelAttribute("exchangeRatesByDate") ExchangeRatesSearchParams searchBy) {
        try {
            exchangeRates = new ExchangeRatesModel().processRequest(searchBy.getExchangeRatesDate());
            errorMessage = "";
        } catch (Exception e) {
            exchangeRates = new ArrayList<>();
            errorMessage = e.getLocalizedMessage();
        }

        exchangeRatesDate = searchBy.getExchangeRatesDate();

        return "redirect:index.html";
    }

}
