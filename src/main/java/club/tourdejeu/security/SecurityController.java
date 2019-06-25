package club.tourdejeu.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    // setting to my custom login form instead of Spring Security default page
    @RequestMapping(value = "/login")
    public String login() {
	return "login";
    }

    // redirecting to a specific page after logout
    @RequestMapping(value = "/logout")
    public String logout() {
	return "redirect:/jeux";
    }

    // setting a default page for the application
    @RequestMapping(value = "/")
    public String home() {
	return "redirect:/jeux";
    }

    // setting a default page for denied access
    @RequestMapping(value = "/403")
    public String accessDenied() {
	return "403";
    }

}
