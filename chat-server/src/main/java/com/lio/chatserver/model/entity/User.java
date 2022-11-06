package com.lio.chatserver.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table( name = "users" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  private Integer id;
  
  @Column( name = "username" )
  private String username;

  @Column( name = "email" )
  private String email;

  @Column( name = "password" )
  private String password;

}