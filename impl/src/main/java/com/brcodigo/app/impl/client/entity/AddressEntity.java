package com.brcodigo.app.impl.client.entity;

import com.brcodigo.app.impl.client.enums.PublicPlaceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {
    @NotNull(message = "O campo 'zipCode' é obrigatório")
    private String zipCode;
    @NotNull(message = "O campo 'publicPlaceType' é obrigatório")
    private PublicPlaceTypeEnum publicPlaceType;
    @NotNull(message = "O campo 'publicPlace' é obrigatório")
    private String publicPlace;
    @NotNull(message = "O campo 'neighborhood' é obrigatório")
    private String neighborhood;
    @NotNull(message = "O campo 'city' é obrigatório")
    private String city;
    @NotNull(message = "O campo 'state' é obrigatório")
    private String state;
    @NotNull(message = "O campo 'country' é obrigatório")
    private String country;
    private String complement;
    private Long number;
}
