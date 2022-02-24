package app.runner;

import app.runner.models.Compilers;
import app.runner.models.ReqBody;
import app.runner.models.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Controller {
    Compilers compilers = new Compilers();
    @Autowired
    ExecutionService service = new ExecutionService();

    @PostMapping(value = "/run", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result buildRequest(@RequestBody ReqBody body) throws IOException {
        Result res = service.runCode(body);
        System.out.println(res.toString());
        return res;
    }

    @GetMapping("/compilers")
    public Set<String> getListOfCompilers() {
        return compilers.getCompilers();
    }
}
