package com.brcodigo.app.impl.client.entity;

import com.brcodigo.app.impl.client.enums.ObservationTypeEnum;
import com.brcodigo.app.impl.client.enums.ObservationVisibilityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ObservationEntity {
    @NotNull(message = "O campo 'userId' é obrigatório")
    private String userId;
    @NotNull(message = "O campo 'observationType' é obrigatório")
    private ObservationTypeEnum observationType;
    @NotNull(message = "O campo 'visibility' é obrigatório")
    private ObservationVisibilityEnum visibility;
    @NotNull(message = "O campo 'message' é obrigatório")
    private String message;
    @NotNull(message = "O campo 'createDate' é obrigatório")
    private LocalDateTime createDate;
}
