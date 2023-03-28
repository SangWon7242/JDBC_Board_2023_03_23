package org.example.controller;

import org.example.Rq;

import java.sql.Connection;
import java.util.Scanner;

public abstract class Controller {
  protected Connection conn;
  protected Scanner scanner;
  protected Rq rq;

  public Controller(Connection conn, Scanner scanner, Rq rq) {
    this.conn = conn;
    this.scanner = scanner;
    this.rq = rq;
  }
}
