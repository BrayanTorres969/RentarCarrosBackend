package com.fastandfuriousrent.services.admin;

import com.fastandfuriousrent.dto.BookACarDto;
import com.fastandfuriousrent.dto.CarDto;
import com.fastandfuriousrent.dto.CarDtoListDto;
import com.fastandfuriousrent.dto.SearchCarDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean postCar(CarDto carDto) throws IOException;

    List<CarDto> getAllCars();

    void deleteCar(Long id);

    CarDto getCarById(Long id);

    boolean updateCar(Long carId,CarDto carDto) throws IOException;

    List<BookACarDto> getBookings();

    boolean changeBookingStatus(Long bookingId, String status);

    CarDtoListDto searchCar(SearchCarDto searchCarDto);
}
