package training.spring.microservice.msnotification;

import lombok.Data;

@Data
public class Message {
    private String      dest;
    private String       msg;
    private EMessageType messageType;
}
