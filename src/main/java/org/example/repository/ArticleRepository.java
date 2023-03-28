package org.example.repository;

import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.sql.Connection;

public class ArticleRepository {
  private Connection conn;
  public ArticleRepository(Connection conn) {
    this.conn = conn;
  }

  public int write(String title, String body) {
    SecSql sql = new SecSql();

    sql.append("INSERT INTO article");
    sql.append(" SET regDate = NOW()");
    sql.append(", updateDate = NOW()");
    sql.append(", title = ?", title);
    sql.append(", `body` = ?", body);

    int id = DBUtil.insert(conn, sql);
    return id;
  }
}
