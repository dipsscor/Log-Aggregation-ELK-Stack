package com.logaggregator.controller;

import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.User;


@RestController
@RequestMapping("/API")
public class UserController {
	

    private final Logger logger = LogManager.getLogger(this.getClass());

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(required = true) final Long id) {

        logger.info("Request with the id =[" + id + "]");

        final User user = new User(id, "User_"+id, "lastname_"+id, new GregorianCalendar());
        logger.info(String.format("id=[%s] firstname=[%s] surname=[%s]", user.getId(), user.getFirstname(), user.getSurname()));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
