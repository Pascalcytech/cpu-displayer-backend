package app;  // Assurez-vous que ce package est correct

import app.entity.CPU;
import app.entity.Socket;
import app.service.CPUService;
import app.service.SocketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CpuApplication.class) // Spécifiez ici votre classe principale
public class CpuApplicationTests {

    @Autowired
    private CPUService cpuService;

    @Autowired
    private SocketService socketService;

    @Test
    public void contextLoads() {
        assertNotNull(cpuService);
        assertNotNull(socketService);
    }

    @Test
    public void testCreateAndFindCPU() {
        Socket socket = new Socket("AM4");
        Socket savedSocket = socketService.saveSocket(socket);

        CPU cpu = new CPU("AMD", "Ryzen 5 3600", savedSocket);
        CPU savedCPU = cpuService.saveCPU(cpu);

        assertNotNull(savedCPU);
        assertEquals("AMD", savedCPU.getBrand());
        assertEquals("Ryzen 5 3600", savedCPU.getModel());
        assertEquals("AM4", savedCPU.getSocket().getName());
    }
}
