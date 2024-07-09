package com.fastandfuriousrent.dto;

import com.fastandfuriousrent.enumeraciones.BookCarStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BookACarDto {
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long price;

    private BookCarStatus bookCarStatus;

    private Long cardId;

    private Long userId;

    private String username;

    private String email;

}
