package com.back.spring_build_test2.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model){

        List<Article> articleList = articleService.findAll();

        model.addAttribute("articleList",articleList);

        return "article_list";
    }

    @GetMapping("/create")
    public String getAddContent(){

        return "article_create";
    }

    @PostMapping("/create")
    public String postAddContent(@RequestParam(value = "title") String title,
                                 @RequestParam(value = "content") String content){

        articleService.create(title,content);
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detailPage(@PathVariable("id") BigInteger id, Model model){

        Article article = articleService.findById(id);
        model.addAttribute("article",article);

        return "article_detail";
    }

    @GetMapping("/modify/{id}")
    public String modifyPage(@PathVariable BigInteger id, Model model) {

        Article article = articleService.findById(id);

        model.addAttribute("article", article);

        return "article_modify";
    }


    @PostMapping("/modify/{id}")
    public String modify(
            @PathVariable BigInteger id,
            @RequestParam String title,
            @RequestParam String content) {

        articleService.modify(id, title, content);

        return "redirect:/article/detail/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteArticle(@PathVariable BigInteger id)
    {
        articleService.delete(id);
        return "redirect:/article/list";
    }
}
