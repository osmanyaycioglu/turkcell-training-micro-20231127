package training.spring.microservice.msorder.integrations.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String       dest;
    private String       msg;
    private EMessageType messageType;
}
