package app.controller;

import app.entity.CPU;
import app.service.CPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cpus")
public class CPUController {

    private final CPUService cpuService;

    @Autowired
    public CPUController(CPUService cpuService) {
        this.cpuService = cpuService;
    }

    // Get all CPUs
    @GetMapping
    public List<CPU> getAllCPUs() {
        return cpuService.getAllCPUs();
    }

    // Get a specific CPU by ID
    @GetMapping("/{id}")
    public ResponseEntity<CPU> getCPUById(@PathVariable Long id) {
        CPU cpu = cpuService.getCPUById(id);
        if (cpu != null) {
            return ResponseEntity.ok(cpu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new CPU
    @PostMapping
    public CPU createCPU(@RequestBody CPU cpu) {
        return cpuService.saveCPU(cpu);
    }

    // Update an existing CPU
    @PutMapping("/{id}")
    public ResponseEntity<CPU> updateCPU(@PathVariable Long id, @RequestBody CPU updatedCPU) {
        CPU cpu = cpuService.updateCPU(id, updatedCPU);
        if (cpu != null) {
            return ResponseEntity.ok(cpu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a CPU by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCPU(@PathVariable Long id) {
        if (cpuService.deleteCPU(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
