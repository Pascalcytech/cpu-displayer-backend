package app.service;

import app.entity.CPU;
import app.repository.CPURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CPUService {

    private final CPURepository cpuRepository;

    @Autowired
    public CPUService(CPURepository cpuRepository) {
        this.cpuRepository = cpuRepository;
    }

    // Get all CPUs
    public List<CPU> getAllCPUs() {
        return cpuRepository.findAll();
    }

    // Get CPU by ID
    public CPU getCPUById(Long id) {
        return cpuRepository.findById(id).orElse(null);
    }

    // Save a new CPU (this is the missing method)
    public CPU saveCPU(CPU cpu) {
        return cpuRepository.save(cpu);
    }

    // Update an existing CPU
    public CPU updateCPU(Long id, CPU updatedCPU) {
        return cpuRepository.findById(id).map(cpu -> {
            cpu.setBrand(updatedCPU.getBrand());
            cpu.setModel(updatedCPU.getModel());
            // Assuming updatedCPU.getSocket() gives you the full Socket object
            cpu.setSocket(updatedCPU.getSocket()); // This should automatically update socket_id in the database
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
