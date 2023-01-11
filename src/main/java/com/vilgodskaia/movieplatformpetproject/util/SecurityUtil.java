package com.vilgodskaia.movieplatformpetproject.util;

public class SecurityUtil {
    public static final String HAS_AUTHORITY_ADMIN = "hasAuthority('ADMIN')";
    public static final String HAS_AUTHORITY_ADMIN_OR_CLIENT = "hasAnyAuthority({'ADMIN', 'CLIENT'})";
}
