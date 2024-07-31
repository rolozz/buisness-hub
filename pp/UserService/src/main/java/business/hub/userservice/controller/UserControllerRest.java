package business.hub.userservice.controller;

import business.hub.userservice.DTO.UserDTO;
import business.hub.userservice.model.User;
import business.hub.userservice.repositories.RoleRepositories;
import business.hub.userservice.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST-контроллер для управления пользователями.
 * Обрабатывает HTTP-запросы, связанные с созданием, получением, обновлением и удалением пользователей.
 */
@RestController
@RequestMapping("/users")
public class UserControllerRest {

    private final UserServices userServices;
    private final RoleRepositories roleRepositories;

    /**
     * Конструктор контроллера с инъекцией зависимостей.
     *
     * @param userServicesParam   Сервис пользователей.
     * @param roleRepositoriesParam Репозиторий ролей.
     */
    @Autowired
    public UserControllerRest(final UserServices userServicesParam,
                              final RoleRepositories roleRepositoriesParam) {
        this.userServices = userServicesParam;
        this.roleRepositories = roleRepositoriesParam;
    }


    /**
     * Создание нового пользователя.
     *
     * @param user Данные нового пользователя.
     * @return Сохраненный пользователь.
     */
    @PostMapping()
    public User saveUser(final @RequestBody User user) {

        userServices.saveUser(user);
        return user;
    }


    /**
     * Получение всех пользователей.
     *
     * @return Список всех пользователей.
     */
    @GetMapping()
    public List<User> getUsers() {
        return userServices.getAllUsers();
    }

    /**
     * Получение пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Пользователь с указанным идентификатором.
     */
    @GetMapping("/{id}")
    public User getUser(final @PathVariable("id") int id) {
        return userServices.getUser(id);
    }

    /**
     * Обновление информации о пользователе.
     *
     * @param user Обновленные данные о пользователе.
     * @return Обновленный пользователь.
     */
    @PutMapping()
    public User putUser(final @RequestBody User user) {
        userServices.saveUser(user);
        return user;
    }

    /**
     * Удаление пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя для удаления.
     * @return Сообщение об успешном удалении пользователя.
     */
    @DeleteMapping("/{id}")
    public String delUser(final @PathVariable("id") int id) {
        userServices.deleteUser(id);
        return "delete user " + id;
    }

    /**
     * Endpoint для поиска пользователя по email.
     *
     * @param email Email пользователя, которого необходимо найти.
     * @return ResponseEntity с найденным пользователем или статусом 404, если пользователь не найден.
     */
    @GetMapping("/find")
    public ResponseEntity<User> findUserByEmail(@RequestParam String email) {
        Optional<User> userOptional = userServices.findByEmail(email);
        return userOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    /**
     * Endpoint для регистрации нового пользователя.
     *
     * @param userDTO Data Transfer Object (DTO) с информацией о пользователе для регистрации.
     * @return ResponseEntity с зарегистрированным пользователем (DTO).
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userServices.registerUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

    /**
     * Endpoint для подтверждения пользователя по коду подтверждения.
     *
     * @param code Код подтверждения.
     * @return ResponseEntity с сообщением о результате операций подтверждения. Возвращает статус 200, если подтверждение успешно, иначе статус 400.
     */
    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String code) {
        boolean isVerified = userServices.verifyUser(code);
        if (isVerified) {
            return ResponseEntity.ok("Email verified successfully!");
        } else {
            return ResponseEntity.status(400).body("Invalid or expired confirmation code");
        }
    }

    /**
     * Endpoint для повторной отправки кода подтверждения пользователю.
     *
     * @param userId Идентификатор пользователя, для которого необходимо отправить новый код подтверждения.
     * @return ResponseEntity с сообщением о результате операции. Возвращает статус 200, если код успешно отправлен.
     */
    @PostMapping("/resend-code/{userId}")
    public ResponseEntity<String> resendConfirmationCode(@PathVariable Integer userId) {
        userServices.renewConfirmationCode(userId);
        return ResponseEntity.ok("New confirmation code sent successfully!");
    }
}




