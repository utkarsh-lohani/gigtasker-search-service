package com.gigtasker.searchservice.dto;

import com.gigtasker.searchservice.enums.TaskStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TaskDTO(
        Long id,
        String title,
        String description,
        TaskStatusEnum status,
        BigDecimal minPay,
        BigDecimal maxPay,
        LocalDateTime deadline) {}
