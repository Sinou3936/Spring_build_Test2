package com.back.spring_build_test2.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ArticleRespoitory extends JpaRepository<Article, BigInteger> {

    List<Article> findByTitleContaining(String keyword);
}
