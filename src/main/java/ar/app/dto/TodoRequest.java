package ar.app.dto;

public record TodoRequest(
        String title,
        String description,
        String targetDate,
        boolean isDone
) {
}
