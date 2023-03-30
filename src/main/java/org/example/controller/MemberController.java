package org.example.controller;

import org.example.Rq;
import org.example.dto.Member;
import org.example.service.MemberService;

import java.sql.Connection;
import java.util.Scanner;

public class MemberController extends Controller {
  private MemberService memberService;

  public MemberController(Connection conn, Scanner sc, Rq rq) {
    super(conn, sc, rq);
    memberService = new MemberService(conn);
  }

  public void join() {
    String loginId;
    String loginPw;
    String loginPwConfirm;
    String name;

    System.out.println("== 회원 가입 ==");

    // 로그인 아이디 입력
    while (true) {
      System.out.printf("로그인 아이디 : ");
      loginId = scanner.nextLine().trim();

      if (loginId.length() == 0) {
        System.out.println("로그인 아이디를 입력해주세요.");
        continue;
      }

      boolean isLoginIdDup = memberService.isLoginIdDup(loginId);

      if (isLoginIdDup) {
        System.out.printf("%s(은)는 이미 사용중인 로그인 아이디입니다.\n", loginId);
        continue;
      }

      break;
    }

    // 로그인 비밀번호 입력
    while (true) {
      System.out.printf("로그인 비밀번호 : ");
      loginPw = scanner.nextLine().trim();

      if (loginPw.length() == 0) {
        System.out.println("로그인 비밀번호를 입력해주세요.");
        continue;
      }

      // 로그인 비밀번호 확인 입력
      boolean loginPwConfirmIsSame = true;

      while (true) {
        System.out.printf("로그인 비밀번호 확인 : ");
        loginPwConfirm = scanner.nextLine().trim();

        if (loginPwConfirm.length() == 0) {
          System.out.println("로그인 비밀번호를 입력해주세요.");
          continue;
        }

        if (loginPw.equals(loginPwConfirm) == false) {
          System.out.println("로그인 비밀번호가 일치하지 않습니다.");
          loginPwConfirmIsSame = false;
          break;
        }
        break;
      }

      if (loginPwConfirmIsSame) {
        break;
      }
    }

    // 이름 입력
    while (true) {
      System.out.printf("이름 : ");
      name = scanner.nextLine().trim();

      if (name.length() == 0) {
        System.out.println("이름을 입력해주세요.");
        continue;
      }
      break;
    }

    int id = memberService.join(loginId, loginPw, name);

    System.out.printf("%d번 회원이 등록되었습니다.\n", id);
  }


  public void login() {
    String loginId;
    String loginPw;

    System.out.println("== 로그인 ==");

    System.out.printf("로그인 아이디 : ");
    loginId = scanner.nextLine().trim();

    if (loginId.length() == 0) {
      System.out.println("로그인 아이디를 입력해주세요.");
      return;
    }

    Member member = memberService.getMemberByLoginId(loginId);

    if (member == null ) {
      System.out.println("입력하신 로그인 아이디는 존재하지 않습니다.");
      return;
    }

    int tryMaxCount = 3;
    int tryCount = 0;

    // 로그인 비밀번호 입력
    while (true) {
      if(tryCount >= tryMaxCount) {
        System.out.println("비밀번호 확인 후 다음에 다시 시도해주세요.");
        break;
      }

      System.out.printf("로그인 비밀번호 : ");
      loginPw = scanner.nextLine().trim();

      if (loginPw.length() == 0) {
        System.out.println("로그인 비밀번호를 입력해주세요.");
        continue;
      }

      if(member.getLoginPw().equals(loginPw) == false) {
        System.out.println("비밀번호가 일치하지 않습니다.");
        tryCount++;
        continue;
      }

      System.out.printf("\"%s\"님 환영합니다.\n", member.getName());
    }
  }
}