package org.usra.creditApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EmailRequest {
    private String receiverEmail;
    private String subject;
    private String emailPayload;

}
