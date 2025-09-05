package com.brcodigo.app.impl.client.entity;

import com.brcodigo.app.impl.client.enums.ContactTypeEnum;
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
public class ContactEntity {
    @NotNull(message = "O campo 'contactType' é obrigatório")
    private ContactTypeEnum contactType;
    @NotNull(message = "O campo 'phoneNumber' é obrigatório")
    private String phoneNumber;
    @NotNull(message = "O campo 'preference' é obrigatório")
    private Boolean preference;
    @NotNull(message = "O campo 'createDate' é obrigatório")
    private LocalDateTime createDate;
}
