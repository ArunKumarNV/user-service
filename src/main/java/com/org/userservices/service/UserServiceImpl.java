package com.org.userservices.service;

import com.org.userservices.entity.User;
import com.org.userservices.repository.UserRepository;
import com.org.userservices.service.dto.DepartmentDTO;
import com.org.userservices.service.dto.ResponseDTO;
import com.org.userservices.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
//    private RestTemplate restTemplate;
//    private WebClient webClient;
    private APIClient apiClient;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

/*    @Override
    public ResponseDTO getUser(Long userId) {
        ResponseDTO responseDTO = new ResponseDTO();
        User user = userRepository.findById(userId).get();
        UserDTO userDTO = mapToUser(user);

        ResponseEntity<DepartmentDTO> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/api/departments/"+user.getDepartmentId(),DepartmentDTO.class);

        DepartmentDTO departmentDTO = responseEntity.getBody();
        DepartmentDTO departmentDTO = webClient.get()
                        .uri("http://localhost:8080/api/departments/"+user.getDepartmentId())
                                .retrieve()
                                        .bodyToMono(DepartmentDTO.class)
                                                .block();

        System.out.println(responseEntity.getStatusCode());

        responseDTO.setUser(userDTO);
        responseDTO.setDepartment(departmentDTO);

        return responseDTO;
    }*/

    @Override
    public ResponseDTO getUser(Long userId) {
        ResponseDTO responseDTO = new ResponseDTO();
        User user = userRepository.findById(userId).get();
        UserDTO userDTO = mapToUser(user);

        DepartmentDTO departmentDTO = apiClient.getDepartmentById(Long.valueOf(user.getDepartmentId()));
        responseDTO.setDepartment(departmentDTO);
        responseDTO.setUser(userDTO);

        return responseDTO;
    }

    private UserDTO mapToUser(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
