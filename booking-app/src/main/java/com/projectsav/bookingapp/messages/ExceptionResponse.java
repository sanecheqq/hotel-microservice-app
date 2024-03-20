package com.projectsav.bookingapp.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExceptionResponse(
        @JsonProperty("error_message")
        String errorMessage
) {
}
