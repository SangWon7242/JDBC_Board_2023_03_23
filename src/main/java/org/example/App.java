package org.example;

import org.example.controller.ArticleController;
import org.example.controller.MemberController;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
  public void run() {
    Scanner sc = Container.scanner;

    while (true) {
      System.out.printf("명령어) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      // DB 연결
      Connection conn = null;

      try {
        Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) {
        System.out.println("예외 : MySQL 드라이버 로딩 실패");
        System.out.println("프로그램을 종료합니다.");
        break;
      }

      String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

      try {
        conn = DriverManager.getConnection(url, "sbsst", "sbs123414");

        // action 메서드 실행
        action(conn, sc, rq, cmd);

      } catch (SQLException e) {
        System.out.println("예외 : MySQL 드라이버 로딩 실패");
        System.out.println("프로그램을 종료합니다.");
        break;
      } finally {
        try {
          if (conn != null && !conn.isClosed()) {
            conn.close();
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      // DB 연결 끝
    }
    sc.close();
  }

  private void action(Connection conn, Scanner sc, Rq rq, String cmd) {
    ArticleController articleController = new ArticleController();
    articleController.setConn(conn);
    articleController.setScanner(sc);
    articleController.setRq(rq);

    MemberController memberController = new MemberController();
    memberController.setConn(conn);
    memberController.setScanner(sc);
    memberController.setRq(rq);


    if (cmd.equals("/usr/member/join")) {
      memberController.join();
    } else if (rq.getUrlPath().equals("/usr/article/write")) {
      articleController.write();
    } else if (rq.getUrlPath().equals("/usr/article/list")) {
      articleController.showList();
    } else if(rq.getUrlPath().equals("/usr/article/detail")) {
      articleController.showDetail();
    } else if (rq.getUrlPath().equals("/usr/article/modify")) {
      articleController.modify();
    } else if (rq.getUrlPath().equals("/usr/article/delete")) {
     articleController.delete();
    } else if (cmd.equals("system exit")) {
      System.out.println("시스템 종료");
      System.exit(0);
    } else {
      System.out.println("명령어를 확인해주세요.");
    }
    return;
  }

}
