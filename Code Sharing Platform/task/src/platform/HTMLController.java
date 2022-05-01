package platform;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class HTMLController {
    @Autowired
    private CodeService codeService;


    @GetMapping("/code/{uuid}")
    public String getAsHTML (@PathVariable String uuid, Model model){

        Code code = codeService.getSnippet(uuid);
        model.addAttribute("code", code.getCode());
        model.addAttribute("date", code.getDate());

        if (code.isHasViewLimit()) {
            model.addAttribute("views", code.getViews());
        }
        else {
            model.addAttribute("views", -1);
        }

        if (code.isHasTimeLimit()) {
            model.addAttribute("time", code.getTime());
        }
        else {
            model.addAttribute("time", -1);
        }

        return "code";
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String enterCode(){
        return "newcode";
    }

    @GetMapping("code/latest")
    public String getLatestSnippets(Model model){
        List<Code> tenLatest = codeService.getTenLatest();
        model.addAttribute("code_snippets", tenLatest);
        return "latest";
    }
}
