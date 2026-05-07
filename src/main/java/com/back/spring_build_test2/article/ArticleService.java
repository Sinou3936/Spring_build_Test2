package com.back.spring_build_test2.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRespoitory articleRespoitory;

    public List<Article> findAll() {
        return articleRespoitory.findAll();
    }

    public void create(String title, String content)
    {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());

        articleRespoitory.save(article);
    }

    public Article findById(BigInteger id) {
        Optional<Article> article = articleRespoitory.findById(id);

        return article.get();
    }

    public void modify(BigInteger id, String title, String content) {
            Article article = articleRespoitory.findById(id)
                            .orElseThrow(() -> new RuntimeException("Article not found"));

            article.setTitle(title);
            article.setContent(content);
            articleRespoitory.save(article);
    }

    public void delete(BigInteger id) {
        articleRespoitory.deleteById(id);
    }
}
