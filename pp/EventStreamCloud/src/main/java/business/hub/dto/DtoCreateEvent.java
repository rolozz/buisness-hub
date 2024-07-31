package business.hub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * author Igor Ostrovsky
 * Класс DataTransferObject будет получать данные из Kafka dto-topic
 * для создания объекта ProfileEvent
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoCreateEvent {

    @JsonProperty("account_id")
    String accountId;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;

}
