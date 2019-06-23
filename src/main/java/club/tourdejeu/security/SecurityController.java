package club.tourdejeu.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    @RequestMapping(value = "/login")
    public String login() {
	return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
	return "redirect:/jeux";
    }

    @RequestMapping(value = "/")
    public String home() {
	return "redirect:/jeux";
    }

    @RequestMapping(value = "/403")
    public String accessDenied() {
	return "403";
    }

}
