package ar.app.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TodoPaginationResponse(
        List<TodoResponse> todos,
        Integer pageNumber,
        Integer pageSize,
        Integer totalPages,
        Long totalElements

) {
}
