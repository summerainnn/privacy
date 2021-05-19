package summerain.privacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import summerain.privacy.bean.User;
import summerain.privacy.service.UserService;

import java.util.List;


/**
 * @author ：summerain
 * @date ：Created in 2021/5/7 4:59 下午
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/admin.html")
    public String admin(
            Model model
    ){
        List<User> userList = userService.getAllUser();
        System.out.println(userList.toString());
        model.addAttribute("userList",userList);
        return "admin";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Integer userId
    ){
        userService.delete(userId);
        return "redirect:/admin.html";
    }
}
