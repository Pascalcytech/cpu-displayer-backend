package app.service;

import app.entity.CPU;
import app.entity.Socket;
import app.repository.CPURepository;
import app.repository.SocketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CPUService {

    private final CPURepository cpuRepository;
    private final SocketRepository socketRepository; // Define SocketRepository here

    @Autowired
    public CPUService(CPURepository cpuRepository, SocketRepository socketRepository) { // Add SocketRepository to the constructor
        this.cpuRepository = cpuRepository;
        this.socketRepository = socketRepository;
    }

    // Get all CPUs
    public List<CPU> getAllCPUs() {
        return cpuRepository.findAll();
    }

    // Get CPU by ID
    public CPU getCPUById(Long id) {
        return cpuRepository.findById(id).orElse(null);
    }

    // Save a new CPU
    public CPU saveCPU(CPU cpu) {
        return cpuRepository.save(cpu);
    }

    // Update an existing CPU
    public CPU updateCPU(Long id, CPU updatedCPU) {
        return cpuRepository.findById(id).map(cpu -> {
            cpu.setBrand(updatedCPU.getBrand());
            cpu.setModel(updatedCPU.getModel());

            // Fetch the Socket entity using the socket ID from updatedCPU
            if (updatedCPU.getSocket() != null && updatedCPU.getSocket().getId() != null) {
                Optional<Socket> socket = socketRepository.findById(updatedCPU.getSocket().getId());
                if (socket.isPresent()) {
                    cpu.setSocket(socket.get()); // Set the persistent Socket entity
                } else {
                    System.err.println("Invalid socket ID: " + updatedCPU.getSocket().getId());
                    throw new IllegalArgumentException("Invalid socket ID for update.");
                }
            } else {
                System.err.println("Socket ID is null or missing.");
                throw new IllegalArgumentException("Socket ID must be provided for update.");
            }

            return cpuRepository.save(cpu);
        }).orElse(null);
    }

    // Delete a CPU
    public boolean deleteCPU(Long id) {
        if (cpuRepository.existsById(id)) {
            cpuRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
