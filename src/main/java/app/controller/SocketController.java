package app.controller;

import app.entity.Socket;
import app.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sockets")
public class SocketController {

    private final SocketService socketService;

    @Autowired
    public SocketController(SocketService socketService) {
        this.socketService = socketService;
    }

    // Get all Sockets
    @GetMapping
    public List<Socket> getAllSockets() {
        return socketService.getAllSockets();
    }

    // Get a specific Socket by ID
    @GetMapping("/{id}")
    public ResponseEntity<Socket> getSocketById(@PathVariable Long id) {
        Optional<Socket> socket = socketService.getSocketById(id);

        // Check if socket is present and return it, otherwise return 404 Not Found
        return socket.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // Not mapping was made for the POST method ...
    @PostMapping
    public ResponseEntity<Socket> createSocket(@RequestBody Socket socket) {
        Socket createdSocket = socketService.createSocket(socket);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSocket);
    }
}
