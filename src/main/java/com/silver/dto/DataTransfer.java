package com.silver.dto;

import jakarta.validation.constraints.Positive;

public record DataTransfer(Long sender, @Positive Double amount, Long receiver) {

}
