package com.fstechspace.fsstore.product.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
    Long id,
    String name,
    BigDecimal price,
    Long version,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
