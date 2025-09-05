package com.brcodigo.app.impl.order.entity;

import com.brcodigo.app.impl.order.enums.OrderStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document("Order")
public class OrderEntity {
    @Id
    private String id;
    @Schema(description = "ID do Cliente")
    @NotNull(message = "O campo 'clientId' é obrigatório")
    private String clientId;
    @Schema(description = "Total")
    @NotNull(message = "O campo 'total' é obrigatório")
    private BigDecimal total;
    @Schema(description = "Situação do pedido")
    @NotNull(message = "O campo 'status' é obrigatório")
    private OrderStatusEnum status;
    @Schema(description = "Data de criação do pedido")
    @NotNull(message = "O campo 'date' é obrigatório")
    private LocalDate date;
    @Schema(description = "ID do Usuário de Criação")
    @NotNull(message =  "O campo 'createUserId' é obrigatório")
    private String createUserId;
    @Schema(description = "ID do Usuário da Atualização")
    @NotNull(message =  "O campo 'updateUserId' é obrigatório")
    private String updateUserId;
    @Schema(description = "Data de criação do pedido")
    @NotNull(message = "O campo 'createDate' é obrigatório")
    private LocalDateTime createDate;
    @Schema(description = "Data de criação do pedido")
    @NotNull(message = "O campo 'updateDate' é obrigatório")
    private LocalDateTime updateDate;
}
