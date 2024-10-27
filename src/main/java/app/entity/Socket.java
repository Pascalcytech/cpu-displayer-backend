package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import java.util.Set;

@Entity
@Table(name = "sockets")
public class Socket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "socket")
    @JsonIgnore // Prevents the cpus field from being serialized
    private Set<CPU> cpus;

    // Constructors
    public Socket() {
    }

    public Socket(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CPU> getCpus() {
        return cpus;
    }

    public void setCpus(Set<CPU> cpus) {
        this.cpus = cpus;
    }
}
