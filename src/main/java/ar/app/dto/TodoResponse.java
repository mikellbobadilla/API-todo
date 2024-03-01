package ar.app.dto;

public record TodoResponse(
        Long id,
        String title,
        String description,
        String targetDate,
        boolean isDone
) {
}
