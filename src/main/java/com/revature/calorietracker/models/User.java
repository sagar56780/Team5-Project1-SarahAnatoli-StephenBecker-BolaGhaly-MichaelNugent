package com.revature.calorietracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.calorietracker.models.auth.Role;
import com.revature.calorietracker.models.auth.Token;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"foodLogs","bmiRecords", "exerciseLogs"}) //ignore lazily loaded fields because they may not be available
public class User implements UserDetails {
    public User(Long id) {
        this.id = id;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstName;
    private String lastName;
    private Integer age;
    private Double weight; //metric system? kilograms?
    private Double height; //metric system? meters?
    private String gender;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private Integer dailyCalorieGoal;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany
    @JoinColumn(name = "food_item_id")
    private List<FoodItem> foodLogs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BMIRecord> bmiRecords;

    @OneToMany(mappedBy = "user")
    private List<UserExerciseLog> exerciseLogs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Token> tokens;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }
}

