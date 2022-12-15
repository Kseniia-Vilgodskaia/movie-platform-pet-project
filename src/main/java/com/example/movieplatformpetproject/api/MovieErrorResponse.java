package com.example.movieplatformpetproject.api;

import java.util.List;

public record MovieErrorResponse(List<String> errorMessage, int status) {

}
