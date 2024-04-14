package africa.semicolon.classified.services;

import africa.semicolon.classified.data.model.User;
import africa.semicolon.classified.data.repository.UserRepository;
import africa.semicolon.classified.dtos.DeleteRequest;
import africa.semicolon.classified.dtos.LogOutRequest;
import africa.semicolon.classified.dtos.LoginRequest;
import africa.semicolon.classified.dtos.RegisterRequest;
import africa.semicolon.classified.exceptions.IncorrectPasswordException;
import africa.semicolon.classified.exceptions.UserAlreadyExistException;
import africa.semicolon.classified.exceptions.UserNotFoundException;
import africa.semicolon.classified.response.DeleteReturnResponse;
import africa.semicolon.classified.response.LogOutResponse;
import africa.semicolon.classified.response.LoginResponse;
import africa.semicolon.classified.response.RegisterResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.classified.utilities.MapperClass.*;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    @Autowired
   private UserRepository userRepository;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if (registerRequest.getUsername().isBlank() || registerRequest.getPassword().isBlank()){
            throw new IllegalArgumentException("username or password cannot be empty");
        }
        validateUser(registerRequest.getUsername());
        User newUser = map(registerRequest);
        User saveUser = userRepository.save(newUser);
        return mapRegResponse(saveUser);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User foundUser = mapLoginResponse(loginRequest);
        User userFound = userRepository.findByUsername(foundUser.getUsername().toLowerCase());
        if (isPasswordIncorrect(userFound, loginRequest.getPassword())) {
            throw new IncorrectPasswordException("username or password incorrect");
        }
        userFound.setLocked(false);
        userRepository.save(userFound);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Successfully login");
        return loginResponse;
    }
    @Override
    public LogOutResponse logOut(LogOutRequest logOutRequest){
        User setFoundUser = mapLogOutResponse(logOutRequest);
        User foundUser =  userRepository.findByUsername(setFoundUser.getUsername());
        foundUser.setLocked(true);
        userRepository.save(foundUser);
        LogOutResponse logOutResponse = new LogOutResponse();
        logOutResponse.setMessage("logout is successful");
        return logOutResponse;
    }
    @Override
    public DeleteReturnResponse deleteByUsername(DeleteRequest deleteRequest) {
        User foundUser =  userRepository.findByUsername(deleteRequest.getUsername());
        if (foundUser == null){
            throw new UserNotFoundException("user not found");
        }
        userRepository.delete(foundUser);
        DeleteReturnResponse response = new DeleteReturnResponse();
        response.setMessage("User has been successfully deleted");
        return response;
    }
    @Override
    public long getNumberOfRegisteredUsers() {
        return userRepository.count();
    }
    private void validateUser(String username) {
        var foundUser = userRepository.findById(username);
        if (foundUser.isPresent())
            throw new UserAlreadyExistException("user already registered, login");
    }
}
