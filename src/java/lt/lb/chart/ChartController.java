package lt.lb.chart;

import java.util.ArrayList;
import java.util.List;
import lt.lb.currencies.CurrenciesModel;
import lt.lb.currencies.CurrenciesWrapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChartController {

    private static String errorMessage = "";
    private static List<CurrenciesWrapper> currencies = new ArrayList<>();
    private static List<ChartWrapper> chart = new ArrayList<>();

    static {
        try {
            currencies = new CurrenciesModel().processRequest();
        } catch (Exception e) {
            errorMessage = e.getLocalizedMessage();
        }
    }

    private String selectedCurrency = "";
    private String exchangeRatesDateLow = "";
    private String exchangeRatesDateHigh = "";

    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    public String chart(@ModelAttribute("model") ModelMap modelMap, Model model) {
        modelMap.addAttribute("currencies", currencies);
        modelMap.addAttribute("chart", chart);
        
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("chartLoaded", chart != null && !chart.isEmpty());
        model.addAttribute("selectedCurrency", selectedCurrency);
        model.addAttribute("exchangeRatesDateLow", exchangeRatesDateLow);
        model.addAttribute("exchangeRatesDateHigh", exchangeRatesDateHigh);

        return "chart";
    }

    @RequestMapping(value = "/showChartByCurrencieAndPeriod", method = RequestMethod.POST)
    public String showChartByCurrencieAndPeriod(@ModelAttribute("chartByCurrencieAndPeriod") ChartSearchParams searchBy) {
        try {
            chart = new ChartModel().processRequest(searchBy.getCurrency(), searchBy.getExchangeRatesDateLow(), searchBy.getExchangeRatesDateHigh());
            errorMessage = "";
        } catch (Exception e) {
            chart = new ArrayList<>();
            errorMessage = e.getLocalizedMessage();
        }
        
        selectedCurrency = searchBy.getCurrency();
        exchangeRatesDateLow = searchBy.getExchangeRatesDateLow();
        exchangeRatesDateHigh = searchBy.getExchangeRatesDateHigh();

        return "redirect:chart.html";
    }

}
