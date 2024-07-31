package bussines.hub.notificationservice.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegistrationEvent {

    @NotNull
    @Email
    @JsonProperty("email")
    String email;

    @NotNull
    @JsonProperty("confirmationCode")
    String confirmationCode;
}

