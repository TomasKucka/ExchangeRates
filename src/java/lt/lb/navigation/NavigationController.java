package lt.lb.navigation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NavigationController {

    @RequestMapping(value = "/navigate", method = RequestMethod.POST)
    public String navigate(@ModelAttribute("navigation") NavigationWrapper navigation) {
        if (navigation.getCurrencyChartPageButton() != null) {
            return "redirect:chart.html";
        }
        
        return "redirect:index.html";
    }
    
}
