package com.brcodigo.app.impl.order.entity;

import com.brcodigo.app.impl.order.enums.OrderPaymentStatusEnum;
import com.brcodigo.app.impl.order.enums.OrderPaymentTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document("OrderPayment")
public class OrderPaymentEntity {
    @Id
    private String id;
    @Schema(description = "ID do Pedido")
    @NotNull(message = "O campo 'orderId' é obrigatório")
    private String orderId;
    @Schema(description = "Valor do Pago")
    @NotNull(message = "O campo 'value' é obrigatório")
    private BigDecimal value;
    @Schema(description = "Date e Hora do Pagamento")
    private LocalDateTime paymentDateTime;
    @Schema(description = "Tipo do pagamento")
    @NotNull(message = "O campo 'paymentType' é obrigatório")
    private OrderPaymentTypeEnum paymentType;
    @Schema(description = "ID  Externo (uso em integrações)")
    private String externalId;
    @Schema(description = "Status do pagamento")
    @NotNull(message = "O campo 'status' é obrigatório")
    private OrderPaymentStatusEnum status;
}
