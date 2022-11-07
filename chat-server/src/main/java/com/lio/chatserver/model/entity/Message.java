package com.lio.chatserver.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "messages" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  private Integer id;

  @Column( name = "text" )
  private String text;

  @OneToOne
  private User user;

  @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
  private LocalDateTime sentDate;
  
}