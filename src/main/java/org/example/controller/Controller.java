package org.example.controller;

import org.example.Rq;

import java.sql.Connection;
import java.util.Scanner;

public abstract class Controller {
  protected Connection conn;
  protected Scanner scanner;
  protected Rq rq;


  public void setRq(Rq rq) {
    this.rq = rq;
  }

  public void setConn(Connection conn) {
    this.conn = conn;
  }

  public void setScanner(Scanner scanner) {
    this.scanner = scanner;
  }
}
