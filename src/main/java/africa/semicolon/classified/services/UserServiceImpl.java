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
import africa.semicolon.classified.response.DeleteResponse;
import africa.semicolon.classified.response.LogOutResponse;
import africa.semicolon.classified.response.LoginResponse;
import africa.semicolon.classified.response.RegisterResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static africa.semicolon.classified.utilities.MapperClass.*;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    @Autowired
   private UserRepository userRepository;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if (registerRequest.getUsername().toLowerCase().isBlank() || registerRequest.getPassword().toLowerCase().isBlank()){
            throw new IllegalArgumentException("username or password cannot be empty");
        }
        validateUser(registerRequest.getUsername().toLowerCase());
        User newUser = map(registerRequest);
        User saveUser = userRepository.save(newUser);
        return mapRegResponse(saveUser);
    }
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User foundUser = userRepository.findByUsername(loginRequest.getUsername().toLowerCase());
        if (isPasswordIncorrect(foundUser, loginRequest.getPassword().toLowerCase())) {
            throw new IncorrectPasswordException("username or password incorrect");
        }
        foundUser.setLocked(false);
        userRepository.save(foundUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Successfully logged-in");
        return loginResponse;
    }
    @Override
    public LogOutResponse logOut(LogOutRequest logOutRequest){
        User foundUser =  userRepository.findByUsername(logOutRequest.getUsername().toLowerCase());
        foundUser.setLocked(true);
        userRepository.save(foundUser);
        LogOutResponse logOutResponse = new LogOutResponse();
        logOutResponse.setMessage("successful logged-out");
        return logOutResponse;
    }
    @Override
    public DeleteResponse deleteByUsername(DeleteRequest deleteRequest) {
        User foundUser =  userRepository.findByUsername(deleteRequest.getUsername().toLowerCase());
        if (foundUser == null){
            throw new UserNotFoundException("user not found");
        }
        checkAccountState(foundUser);
        userRepository.delete(foundUser);
        DeleteResponse response = new DeleteResponse();
        response.setMessage("User has been successfully deleted");
        return response;
    }
    @Override
    public long getNumberOfRegisteredUsers() {
        return userRepository.count();
    }
    public void validateUser(String username) {
        Optional<User> user = userRepository.findUserByUsername(username.toLowerCase());
        if (user.isPresent())
           throw new UserAlreadyExistException("user already registered, login instead");
    }
    public static void checkAccountState(User user){
        if (user.isLocked())
            throw new IllegalArgumentException("please login");
    }
    @Override
    public void checkUser(String username) {
        Optional<User> user = userRepository.findUserByUsername(username.toLowerCase());
        if(user.isEmpty()) throw new UserNotFoundException("User not found");
    }
}
