package com.murgaloids.server.conversation;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name = "conversation")
public class Conversation {
  @Id
  @Column(name="id", columnDefinition="varchar(255)")
  private String id;;

  @Column(name="student1_id", columnDefinition="int(11)")
  private Long student1Id;

  @Column(name="student2_id", columnDefinition="int(11)")
  private Long student2Id;

  protected Conversation() {}

  public Conversation(String id, Long student1Id, Long student2Id) {
    this.id = id;
    this.student1Id = student1Id;
    this.student2Id = student2Id;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getStudent1Id() {
    return this.student1Id;
  }

  public void setStudent1Id(Long id) {
    this.student1Id = id;
  }

  public Long getStudent2Id() {
    return this.student2Id;
  }

  public void setStudent2Id(Long id) {
    this.student2Id = id;
  }
}