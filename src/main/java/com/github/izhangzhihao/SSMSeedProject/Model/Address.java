package com.github.izhangzhihao.SSMSeedProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("Address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private int addrId;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
