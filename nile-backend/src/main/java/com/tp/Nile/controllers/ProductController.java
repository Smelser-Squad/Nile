package com.tp.Nile.controllers;

import com.tp.Nile.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    ProductServiceImpl service;

}
