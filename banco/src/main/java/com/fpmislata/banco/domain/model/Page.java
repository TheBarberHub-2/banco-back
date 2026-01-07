package com.fpmislata.banco.domain.model;

import java.util.List;

public record Page<T>(
        List<T> data,
        int pageNumber,
        int pageSize,
        int totalElements) {
}
