package com.vilgodskaia.movieplatformpetproject.config.exceptions;

import java.util.List;

public record CustomErrorResponse(List<String> errorMessage) {

}
