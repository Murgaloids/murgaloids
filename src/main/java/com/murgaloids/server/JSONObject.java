package com.murgaloids.server;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class JSONObject<T> {
    @NonNull private T value;
    @NonNull private HttpStatus status;
}