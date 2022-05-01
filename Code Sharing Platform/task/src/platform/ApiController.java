package platform;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ApiController {
    @Autowired
    private CodeService codeService;


    @GetMapping("/api/code/{uuid}")
    public Code getAsJson(@PathVariable String uuid){
        return codeService.getSnippet(uuid);
    }

    @PostMapping("/api/code/new")
    public Map<String,String> postCode(@RequestBody Code newCode){
        Code code = new Code();
        code.setDate(CodeService.formatDate());
        code.setCode(newCode.getCode());

        code.setViews(newCode.getViews());
        if(newCode.getViews() > 0) code.setHasViewLimit(true);

        code.setTime(newCode.getTime());
        if(newCode.getTime() > 0) code.setHasTimeLimit(true);

        codeService.addSnippet(code);
        return Map.of("id", code.getUuid());
    }

    @GetMapping("/api/code/latest")
    public List<Code> getLatestSnippets(){

        return codeService.getTenLatest();
    }

}
