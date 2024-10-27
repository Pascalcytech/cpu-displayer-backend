package app.service;

import app.entity.Socket;
import app.repository.SocketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocketService {

    private final SocketRepository socketRepository;

    @Autowired
    public SocketService(SocketRepository socketRepository) {
        this.socketRepository = socketRepository;
    }

    // Get all sockets
    public List<Socket> getAllSockets() {
        return socketRepository.findAll();
    }

    // Get a socket by ID
    public Optional<Socket> getSocketById(Long id) {
        return socketRepository.findById(id);
    }

    // Create a new socket
    public Socket createSocket(Socket socket) {
        return socketRepository.save(socket);
    }

    // Save a socket
    public Socket saveSocket(Socket socket) {
        return socketRepository.save(socket);
    }

    // Update an existing socket
    public Socket updateSocket(Long id, Socket updatedSocket) {
        return socketRepository.findById(id).map(socket -> {
            socket.setName(updatedSocket.getName());
            return socketRepository.save(socket);
        }).orElseThrow(() -> new RuntimeException("Socket not found with id " + id));
    }

    // Delete a socket by ID
    public void deleteSocket(Long id) {
        socketRepository.deleteById(id);
    }
}
