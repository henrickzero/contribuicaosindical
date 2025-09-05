package com.brcodigo.app.impl.client.entity;

import com.brcodigo.app.impl.client.enums.NotificationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {
    @NotNull(message = "O campo 'userId' é obrigatório")
    private String userId;
    @NotNull(message = "O campo 'notificationType' é obrigatório")
    private NotificationTypeEnum notificationType;
    @NotNull(message = "O campo 'active' é obrigatório")
    private Boolean active;
    @NotNull(message = "O campo 'message' é obrigatório")
    private String message;
    @NotNull(message = "O campo 'createDate' é obrigatório")
    private LocalDateTime createDate;
    @NotNull(message = "O campo 'startDate' é obrigatório")
    private LocalDate startDate;
    @NotNull(message = "O campo 'endDate' é obrigatório")
    private LocalDate endDate;
}
