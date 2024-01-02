package pl.mydojo.app.controllers;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestResponse {

    private String role;
    private LocalDateTime time;
    private String loremIpsum;
}
