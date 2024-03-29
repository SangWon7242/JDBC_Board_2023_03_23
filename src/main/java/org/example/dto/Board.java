package org.example.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Board {
  public int id;
  public String regDate;
  public String updateDate;
  public String name;

  public Board(Map<String, Object> boardMap) {
    this.id = (int) boardMap.get("id");
    this.regDate = (String) boardMap.get("regDate");
    this.updateDate = (String) boardMap.get("updateDate");
    this.name = (String) boardMap.get("name");
  }
}
