package com.back.spring_build_test2.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ArticleRespoitory extends JpaRepository<Article, BigInteger> {
}
