package ru.itdt.mobile.sample.order.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressDTO {
    @Schema(description = "Улица доставки")
    private String street;
    @Schema(description = "Номер дома")
    private String building;
    @Schema(description = "Номер квартиры")
    private String flat;
    @Schema(description = "Номер подъезда")
    private Integer entrance;
    @Schema(description = "Этаж")
    private Integer floor;
    @Schema(description = "Код от двери")
    private String doorCode;
    @Schema(description = "Название адреса")
    private String addressName;
    @Schema(description = "Комментарий к адресу")
    private String comment;
}
