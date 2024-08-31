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
@Table(name = "EPICS", schema = "PUBLIC")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Epic {
    @Id
    @Column(name = "EPIC_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "COORDINATOR_ID")
    protected User coordinator;
    @ManyToMany
    @JoinTable(name = "EPIC_TASKS",
            joinColumns = @JoinColumn(name = "EPIC_ID"),
            inverseJoinColumns = @JoinColumn(name = "TASK_ID"))
    protected Set<Task> tasks;
    @Column(nullable = false)
    protected String title;
    @Column(nullable = false)
    protected String description;
    @Enumerated(EnumType.ORDINAL)
    protected TaskStatus status;
    @Column(nullable = false)
    protected int duration;
    @Column(name = "START_DATE")
    protected LocalDate startTime;
    @Column(name = "END_DATE")
    protected LocalDate endTime;
}
