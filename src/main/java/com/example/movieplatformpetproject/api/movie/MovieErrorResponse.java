package com.example.movieplatformpetproject.api.movie;

import java.util.List;

public record MovieErrorResponse(List<String> errorMessage, int status) {

}
