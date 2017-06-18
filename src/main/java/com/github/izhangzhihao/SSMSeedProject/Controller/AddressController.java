package com.github.izhangzhihao.SSMSeedProject.Controller;

import com.github.izhangzhihao.SSMSeedProject.Annotation.RequireAdminOrUser;
import com.github.izhangzhihao.SSMSeedProject.Model.Address;
import com.github.izhangzhihao.SSMSeedProject.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequireAdminOrUser
    @GetMapping("/id/{id}")
    public ResponseEntity<Address> findAddressById(@PathVariable int id) {
        return new ResponseEntity<>(addressService.findAddressById(id), HttpStatus.OK);
    }
}
