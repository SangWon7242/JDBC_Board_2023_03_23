package org.example.service;

import org.example.repository.ArticleRepository;

import java.sql.Connection;

public class ArticleService {
  private ArticleRepository articleRepository;
  public ArticleService(Connection conn) {
    articleRepository = new ArticleRepository(conn);
  }

  public int write(String title, String body) {
    return articleRepository.write(title, body);
  }
}
