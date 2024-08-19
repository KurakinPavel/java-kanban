package ru.kurakin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kurakin.enums.TaskStatus;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "TASKS", schema = "PUBLIC")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @Column(name = "TASK_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "EPIC_ID")
    protected Epic epic;
    @ManyToMany
    @JoinTable(name = "TASK_PERFORMERS",
            joinColumns = @JoinColumn(name = "TASK_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERFORMER_ID"))
    protected Set<User> performers;
    @Column(nullable = false)
    protected String title;
    @Column(nullable = false)
    protected String description;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    protected TaskStatus status;
    @Column(nullable = false)
    protected int duration;
    @Column(name = "START_DATE", nullable = false)
    protected LocalDate startDate;
    @Column(name = "END_DATE", nullable = false)
    protected LocalDate endDate;
}
