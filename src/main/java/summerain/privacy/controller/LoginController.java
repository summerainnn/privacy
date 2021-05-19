package summerain.privacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import summerain.privacy.bean.Collection;
import summerain.privacy.bean.User;
import summerain.privacy.service.CollectionService;
import summerain.privacy.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ：summerain
 * @date ：Created in 2021/5/5 9:09 下午
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    CollectionService collectionService;

    @PostMapping("/login")
    public String login(
            @RequestParam("account") String accountAccount,
            @RequestParam("pwd") String password,
            HttpSession session,
            Model model){
        User user = userService.getUserByAccount(accountAccount);
        if(user!=null){
            if(password.equals(user.getPwd())){
                if(user.getCategory()){
                    session.setAttribute("userId",user.getId());
                    return "redirect:/main.html";
                }else {
                    return "redirect:/admin.html";
                }
            }
        }
        model.addAttribute("msg","用户名或密码错误");
        return "login";
    }

    @GetMapping("/main.html")
    public String main(Model model,HttpSession session){
        User user = userService.getByUserId((Integer) session.getAttribute("userId"));
        model.addAttribute("name",user.getName());
        List<Collection> list = collectionService.getAllByUserId(user.getId());
        System.out.println(list);
        model.addAttribute("collections",list);
        return "main";
    }

    @GetMapping("/")
    public String index(
            HttpSession session,
            Model model
    ){
        if (session.getAttribute("userId") != null){
            model.addAttribute("isLogin","1");
            model.addAttribute("userName",userService.getByUserId((Integer) session.getAttribute("userId")).getName());
        }
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("account") String account,
            @RequestParam("pwd1") String password1,
            @RequestParam("pwd2") String password2,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            Model model
    ){
        if(userService.accountIsDifferent(account)){
            if(password1.equals(password2)){
                userService.insert(account,password1,username,email);
            }else {
                model.addAttribute("msg","密码不一致");
                return "register";
            }
        }else {
            model.addAttribute("msg","账号已存在");
        }
        return "login";
    }

}
