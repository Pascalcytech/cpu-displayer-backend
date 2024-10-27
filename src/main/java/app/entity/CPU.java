package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore; // Importing JsonIgnore
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "cpus")
public class CPU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @ManyToOne
    @JoinColumn(name = "socket_id", nullable = false)
    @JsonIgnore // Prevents the socket field from being serialized
    private Socket socket;

    // New field for socket_id
    @Column(name = "socket_id", nullable = false, insertable = false, updatable = false) // This field is mapped but not directly used
    private Long socket_id;

    // Constructors
    public CPU() {
    }

    public CPU(String brand, String model, Socket socket) {
        this.brand = brand;
        this.model = model;
        this.socket = socket;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    // Getter for socket_id
    public Long getSocket_id() {
        return socket != null ? socket.getId() : null; // Return socket's id if socket is not null
    }
}
