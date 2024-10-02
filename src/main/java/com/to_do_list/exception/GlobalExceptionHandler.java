package com.to_do_list.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<String> handleStatusNotFoundException(StatusNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Map<String, String>>
    handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        } );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex){
        return new ResponseEntity<>("""
                ⠥⢣⡉⢦⠱⣈⠦⡑⡌⠦⡑⢎⡔⢣⢍⡛⠷⣾⣰⡉⢖⡱⢊⡖⡱⢎⡜⢦⡙⢦⡙⢦⡙⡼⢌⡣⡝⣬⢣⡝⣬⢋⡟⢿⣼⣡⢏⡼⣡⢏⡼⣘⢦⡙⢦⡙⢦⡙⢆⠳⡘⢆⠳⡘⢆
                ⡃⢧⠘⡆⢓⡌⡒⡱⡘⠦⣙⠢⡜⡡⢎⡜⡱⢌⡛⢿⣶⣥⣋⠴⡙⢦⡙⢦⡙⢦⡙⢦⣙⠲⣍⠶⣙⠴⡣⢞⡴⢫⡜⣣⠮⣝⡿⣶⣵⣊⠶⣉⠶⣙⢦⡙⢦⡙⡬⢣⡙⣌⢣⡙⢎
                ⣑⠊⡵⢈⢇⡰⢡⠱⣌⠱⣂⠳⢌⡱⢊⠴⡑⢎⡜⠲⣌⡛⡟⣾⣍⡶⣩⢖⡹⢆⡝⣆⢣⢏⡲⡹⣌⢳⡙⢮⠜⣣⡾⠗⠋⠉⠉⠛⢾⣛⢷⣭⣚⡱⢊⡜⢦⠱⣡⠓⡜⢤⠣⡜⠬
                ⡄⢫⠔⣩⠢⢌⡱⠒⣌⠱⢢⠍⢦⠱⣉⢖⡩⣒⠬⡓⠴⣉⠶⡑⠮⡝⣷⣮⣳⢍⠶⣌⠳⣎⠵⡳⢬⢧⣙⣮⠟⠉⠀⠀⠀⠀⠀⠀⠈⣿⡲⢿⡹⢷⣯⣘⢦⡙⢤⢋⡜⢢⠓⡬⢓
                ⠹⣦⣝⡠⢓⠬⢰⢉⠦⡙⡢⠍⣆⣳⣬⡶⠷⢾⢛⠻⣛⢛⡛⡛⣟⢷⣾⣣⢛⡿⣶⣍⡳⡬⣗⣽⠿⠞⣫⠖⠋⠉⠳⣄⠀⠀⠀⠀⣠⣿⢏⢣⡱⢊⠖⣹⢳⢮⣗⠬⡜⣡⢋⠴⣉
                ⡝⢢⠜⡹⢳⣮⣅⢎⣲⣵⠷⢟⡛⠭⡡⢌⠳⡌⢎⡱⢌⠦⡱⠱⣌⠢⡜⠹⣷⣞⣌⢻⢻⣷⠟⠁⠀⠀⣿⠀⠀⠀⠀⢸⡆⠀⣠⣾⢋⡴⢊⠵⣬⣍⣾⣤⣳⣌⡙⠿⢶⣥⣎⠲⡡
                ⢌⠣⡌⢥⢣⣼⠿⡛⡍⢦⡉⠖⡬⢱⡑⠎⢦⢙⣦⣵⣎⣖⣩⣗⣤⡓⣌⠳⢌⢿⣎⣧⡟⠁⠀⠀⠀⠀⠘⣄⡀⠀⢀⡴⠋⠛⢿⣄⣣⣮⠟⠛⠉⠀⠀⠀⠈⠹⣮⡑⢎⡜⢹⠳⣥
                ⢌⢒⣡⣾⠟⡡⢎⡱⡘⢦⡙⡜⡰⢃⡜⡉⢶⡟⠁⠀⠈⠛⠀⠀⠉⠻⡴⣉⠎⢦⢩⡙⡛⠶⣦⣄⡀⠀⠀⠈⠉⠉⠉⠀⠀⠀⠀⠙⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⣹⢺⠆⡜⣡⠓⣌
                ⢌⢢⡿⣁⠎⡔⢣⠒⡍⢦⠱⣌⠱⣃⠦⢹⣘⣧⡀⢠⡞⠩⣹⡆⢀⣴⠷⣡⠚⡤⢣⠜⣡⠓⡴⡘⠹⠷⠶⠶⣶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⢏⢃⠞⡰⢡⠚⡤
                ⠢⣹⡗⢤⢋⡜⣡⢋⠜⣢⠓⣌⡱⣆⣮⢿⣾⡟⠁⠀⠙⠒⠚⠁⠀⠈⣗⢢⡙⡔⢣⠚⡤⢋⠴⣉⢣⡙⣌⠓⡬⢨⡙⢟⡶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡼⢫⠌⡎⡜⣡⢣⡙⡔
                ⠱⢌⢿⣶⣨⣔⣢⣎⣼⡴⣿⢾⣽⣯⣿⣿⠟⣧⡀⠀⢀⣴⣄⠀⣀⡼⢛⠤⡓⣌⢣⡙⠴⣉⠖⡡⢆⡱⢌⡚⢔⠣⡜⣡⢚⢫⣷⡀⠀⠀⠀⠀⠀⠀⣠⢟⡡⢃⠞⡰⡑⢆⠣⡔⣡
                ⡑⢪⠰⣉⠟⠿⠷⠿⠾⢟⡻⣋⠟⡵⣺⡇⠲⣌⠻⡙⢋⡅⢮⡋⢍⠲⣉⠖⡱⢌⠦⣙⢢⡑⢎⡱⢊⡔⢣⠜⣌⠓⡜⢤⠋⣆⢫⣿⡀⠀⠀⠀⣠⣾⣝⡢⢅⠫⣌⠱⣉⢎⡱⡘⡤
                ⠷⣌⠦⡙⡔⢢⠒⡔⢢⡜⣡⠫⣔⢧⣿⢑⣦⡽⠶⠷⠚⠷⠮⢵⣜⣢⡙⡔⠣⡜⢤⢋⡌⠳⣌⠣⢎⡱⢺⡔⣩⢒⡱⢌⠲⣉⢎⣹⣧⠀⢀⣴⡿⣟⠹⣳⢾⣤⡙⠦⡙⣌⢣⢣⠱
                ⢢⢍⡺⣵⣎⡥⣋⠜⣡⠚⣤⠳⣜⢺⣧⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠶⣵⣘⠢⢎⡘⡱⢄⡋⠖⡬⢹⡗⢢⠣⡜⡌⡓⣌⠲⡄⢿⣶⡿⢫⠜⡤⢓⠦⡩⢝⡻⢧⣳⢌⢆⠣⣍
                ⣶⠻⣙⠤⡩⢝⠷⣯⣔⡍⢆⡳⢌⡻⡟⢀⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⢷⣎⡴⢑⠪⠜⡱⢌⡍⣿⡡⢓⡜⡰⠱⡌⡓⡜⢢⢻⣎⠕⡪⢔⡩⣒⠱⣊⡜⢥⢛⠻⢮⣵⡨
                ⢃⠳⣌⠲⣉⢎⡜⣌⠛⡿⢾⣔⣫⡾⢻⡞⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢷⣎⠱⣉⠖⡡⠎⢿⣌⢣⠒⡥⢓⠬⡱⢌⢣⠚⣿⣌⠱⣊⠴⣡⢣⠱⡘⢦⡉⢞⢢⠆⡽
                ⣍⠲⣌⠳⣌⠲⡜⢢⢛⡌⣇⢿⡟⠀⠀⢷⡄⢹⠳⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣷⣈⢎⠱⣹⢸⣿⢢⡙⠴⣉⠖⡱⢊⠦⣩⣹⣦⠓⡥⢚⠤⣃⠧⡙⢦⡙⡜⣢⠹⡰
                ⢆⠳⣌⠱⣌⠳⣘⡱⢪⠔⣎⣾⠀⠀⠀⠀⣻⣜⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣧⢎⡱⢂⣿⣿⣧⡜⡱⢌⢎⡱⣉⠖⣡⠱⣿⢍⠲⣉⠖⣡⢎⡱⢢⡑⢎⡔⢣⠱
                ⢎⡱⢌⠳⣌⠣⠵⣘⠥⣚⠴⣺⣳⣤⣤⡾⣛⡹⣷⣄⠘⠿⠂⠀⠀⠐⠶⣤⣀⣀⠀⠀⡀⠀⡀⢹⠶⢱⣹⣿⡾⣿⣷⡘⡌⢦⠱⣌⠚⡤⢃⣽⡏⡕⢪⠜⡢⢎⡔⢣⠜⣡⠎⡥⢋
                ⠦⡑⢎⡱⢌⡣⢏⠴⣋⠴⣋⠴⣙⢖⡣⢞⡡⢣⢓⡞⢿⢦⣤⡀⠀⠀⠀⠀⠉⠟⢟⠳⠶⢀⡇⣸⣫⣶⡿⢋⣾⡹⣿⣷⡜⣢⠓⣌⠳⢌⠣⢼⡿⣈⢇⡚⡱⢊⡜⡡⢎⡱⢊⡴⢩
                ⢣⡙⢦⠱⣊⢖⡩⢖⡩⠖⣍⠮⣱⢊⡵⢊⡼⢡⠇⣎⡱⢎⠲⣙⠻⠶⣦⣤⣄⣀⣸⣀⣉⣨⡶⠿⠛⢁⣴⠻⣄⠣⡽⣿⡿⣧⡝⡤⢋⠬⣃⣿⣝⣷⣾⣴⢡⠓⡬⡑⢎⡔⢣⠜⣡
                ⡣⢜⢢⢋⡴⢪⠱⢎⡱⣋⡜⡲⢥⡓⢬⠓⣌⠣⠞⡤⢓⠬⡓⣌⠣⣍⣱⢭⡏⠀⠀⠀⠀⠀⠀⢀⡴⠯⣌⠳⣌⠳⡰⣩⠟⣿⣿⣷⣯⣼⣾⣷⢻⠱⣌⢚⡻⢷⣦⡙⢦⠸⣡⠚⡥
                ⡙⢮⣇⡣⡜⢣⣋⢎⠵⣂⢳⠱⢦⡙⢆⢫⠔⣍⠚⡴⡉⢖⡱⢨⡑⠦⣘⣾⡀⠀⠀⠀⠀⣠⣾⠫⠵⢣⢌⠳⣄⢣⠕⣢⠛⡴⢊⡽⣬⡟⡏⢆⢇⠳⡌⢦⡑⢎⠼⡹⠷⣗⢤⢋⠴
                ⡙⢦⣼⡽⢻⢧⣜⡪⣜⡡⢏⢺⠰⣉⠎⢦⡙⢤⢋⠴⣉⠖⠬⣑⣬⡷⠟⣃⠿⣦⠴⣖⠻⣡⠛⡟⣯⣖⣪⠱⡌⢖⡩⢦⡹⣴⠿⣛⠵⡘⢬⢃⠮⡱⢜⢢⡙⢬⠒⡥⢓⡌⢫⠞⡶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                An unexpected error occurred
                                """ + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
