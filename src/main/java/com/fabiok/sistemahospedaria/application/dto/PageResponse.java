package com.fabiok.sistemahospedaria.application.dto;

import java.util.List;

public record PageResponse<T>(
        List<T> content,
        int page,
        int size,
        int totalElementos,
        int totalPages,
        boolean hasNext,
        boolean hasPrevious
) {
}
