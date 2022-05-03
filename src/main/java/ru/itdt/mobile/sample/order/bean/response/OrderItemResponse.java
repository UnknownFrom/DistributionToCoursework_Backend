package ru.itdt.mobile.sample.order.bean.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private Long id;
    @Schema(description = "Название товара", example = "Диван")
    private String name;
    @Schema(description = "Количество", example = "2")
    private Integer quantity;
    @Schema(description = "Цена товара", example = "100500")
    private BigDecimal price;
    @Schema(description = "Картинка товара", example = "link.ru")
    private String thumbnailPath;
}
