
package fhi360.it.assetverify.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.boot.web.servlet.error.ErrorController;

@Controller
public class CustomErrorController implements ErrorController
{
    @RequestMapping({ "/error" })
    public String handleError() {
        return "forward:/index.html";
    }
}
