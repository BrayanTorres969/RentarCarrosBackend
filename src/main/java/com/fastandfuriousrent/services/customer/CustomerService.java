package com.fastandfuriousrent.services.customer;

import com.fastandfuriousrent.dto.BookACarDto;
import com.fastandfuriousrent.dto.CarDto;
import com.fastandfuriousrent.dto.CarDtoListDto;
import com.fastandfuriousrent.dto.SearchCarDto;

import java.util.List;

public interface CustomerService {

    List<CarDto> getAllCars();

    boolean bookACar(Long carId, BookACarDto bookACarDto);

    CarDto getCarById(Long carId);

    List<BookACarDto> getBookingsByUserId(Long userId);

    CarDtoListDto searchCar(SearchCarDto searchCarDto);
}
