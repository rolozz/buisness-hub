package business.hub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketDTO {

    private Long id;

    private String description;

    private String status;
}
