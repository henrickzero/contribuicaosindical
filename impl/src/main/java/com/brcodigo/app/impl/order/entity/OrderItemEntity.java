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

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document("OrderItem")
public class OrderItemEntity {
    @Id
    private String id;
    @Schema(description = "ID do Cliente")
    @NotNull(message = "O campo 'clientId' é obrigatório")
    private String clientId;
    @Schema(description = "ID do Pedido")
    @NotNull(message = "O campo 'orderId' é obrigatório")
    private String orderId;
    @Schema(description = "ID do Item")
    @NotNull(message = "O campo 'itemId' é obrigatório")
    private String itemId;
    @Schema(description = "Valor do Item")
    @NotNull(message = "O campo 'value' é obrigatório")
    private BigDecimal value;
    @Schema(description = "Data de Referencia")
    private LocalDate referenceDate;
    @Schema(description = "Quantidade")
    @NotNull(message = "O campo 'amount' é obrigatório")
    private Long amount;
    @Schema(description = "Situação do pedido")
    @NotNull(message = "O campo 'status' é obrigatório")
    private OrderStatusEnum status;
}
