package com.niveksys.mvcrest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @ApiModelProperty(value = "First Name", required = true)
    private String firstname;

    @ApiModelProperty(value = "Last Name", required = true)
    private String lastname;

    @JsonProperty("customer_url")
    private String customerUrl;
}
