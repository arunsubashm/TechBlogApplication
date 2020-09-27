package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import technicalblog.model.Post;
import technicalblog.service.PostService;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    public HomeController() {
        System.out.println("*** HomeController ***");
    }

    @Autowired
    private PostService postService;

    @RequestMapping("/")

    public String getAllPosts (Model model) {

        List<Post> posts = postService.getAllPosts();

        model.addAttribute("posts", posts);

        return "index2";

    }

    @RequestMapping("/home")

    public String getAllPostsblog (Model model) {

        ArrayList<Post> posts = new ArrayList <>();
        String Msg = "MESSAGE";
        int i = 10;

        Post post1 = new Post();
        post1.setTitle("Post 1");
        post1.setBody("Post Body 1");
        post1.setDate(new Date());

        Post post2 = new Post();
        post2.setTitle("Post 2");
        post2.setBody("Post Body 2");
        post2.setDate(new Date());

        Post post3 = new Post();
        post3.setTitle("Post 3");
        post3.setBody("Post Body 3");
        post3.setDate(new Date());

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);

        model.addAttribute("post1", post1);
        model.addAttribute("message",Msg);
        model.addAttribute("Integer",i);

        return "index";

    }
}
