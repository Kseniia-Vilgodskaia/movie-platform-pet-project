package com.vilgodskaia.movieplatformpetproject.config.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomErrorResponse {
    private final List<String> errorMessage;
}
