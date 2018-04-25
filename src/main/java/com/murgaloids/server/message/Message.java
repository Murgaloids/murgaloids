package com.murgaloids.server.message;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "message")
public class Message {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "id", columnDefinition = "int(11)")
  private Long id;

  @Column(name="conversation_id", columnDefinition="varchar(255)")
  private String conversationId;

  @Column(name="sender_id", columnDefinition="int(11)")
  private Long senderId;

  @Column(name="message", columnDefinition="varchar(1024)")
  private String message;

  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="message_date", columnDefinition="datetime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date messageDate;

  protected Message() {}

  public Message(Long id, String conversationId, Long senderId, String message, Date messageDate) {
    this.id = id;
    this.conversationId = conversationId;
    this.senderId = senderId;
    this.message = message;
    this.messageDate = messageDate;
  }

  public Long getId() {
      return this.id;
  }

  public void setId(Long id) {
      this.id = id;
  }

  public String getConversationId() {
    return this.conversationId;
  }

  public void setConversationId(String id) {
    this.conversationId = id;
  }

  public Long getSenderId() {
    return this.senderId;
  }

  public void setSenderId(Long id) {
    this.senderId = id;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getMessageDate() {
    return this.messageDate;
  }

  public void setMessageDate(Date date) {
    this.messageDate = date;
  }
}