package com.infy.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime localDateTime, 
		String massage, 
		String details, 
		String errorCode) {

}
