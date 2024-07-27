package gift.order.dto;

import java.time.LocalDateTime;

public class CreateOrderResponseDTO {

    long id;
    long optionId;
    int quantity;
    LocalDateTime orderDateTime;
    String message;

    public CreateOrderResponseDTO(long id, long optionId, int quantity, LocalDateTime orderDateTime,
        String message) {
        this.id = id;
        this.optionId = optionId;
        this.quantity = quantity;
        this.orderDateTime = orderDateTime;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public long getOptionId() {
        return optionId;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public String getMessage() {
        return message;
    }
}
