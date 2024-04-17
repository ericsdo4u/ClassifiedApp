package africa.semicolon.classified.services;


import africa.semicolon.classified.dtos.DeleteRequest;
import africa.semicolon.classified.dtos.LogOutRequest;
import africa.semicolon.classified.dtos.LoginRequest;
import africa.semicolon.classified.dtos.RegisterRequest;
import africa.semicolon.classified.response.DeleteResponse;
import africa.semicolon.classified.response.LogOutResponse;
import africa.semicolon.classified.response.LoginResponse;
import africa.semicolon.classified.response.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    RegisterResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
    LogOutResponse logOut(LogOutRequest logOutRequest);
    DeleteResponse deleteByUsername(DeleteRequest deleteRequest);
    void checkUser(String username);
    long getNumberOfRegisteredUsers();

}
