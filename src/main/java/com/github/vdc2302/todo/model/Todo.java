package com.github.vdc2302.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Title cannot be empty")
  @Column(nullable = false)
  private String title;

  private String description;

  @Column(nullable = false)
  private Boolean completed = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonIgnore
  private User user;
}
