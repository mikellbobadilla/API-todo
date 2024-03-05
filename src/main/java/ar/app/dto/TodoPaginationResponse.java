package ar.app.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TodoPaginationResponse(
        List<TodoResponse> content,
        Integer pageNumber,
        Integer pageSize,
        Integer totalPages,
        Long totalElements

) {
}
