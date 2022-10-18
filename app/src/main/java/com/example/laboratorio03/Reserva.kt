package com.example.laboratorio03

import java.io.Serializable


class Reserva : Serializable {
    var firstName: String
    var lastName: String
    var streetAddress1: String
    var streetAddress2: String
    var city: String
    var state: String
    var postal: String
    var country: String
    var countryIndex: Int
    var email: String
    var areaCode: String
    var phoneNumber: String
    var position: String
    var positionIndex: Int
    var startDate: String? = null

    constructor() {
        firstName = ""
        lastName = ""
        streetAddress1 = ""
        streetAddress2 = ""
        city = ""
        state = ""
        postal = ""
        country = ""
        countryIndex = 0
        email = ""
        areaCode = ""
        phoneNumber = ""
        position = ""
        positionIndex = 0
    }

    constructor(
        firstName: String,
        lastName: String,
        streetAddress1: String,
        streetAddress2: String,
        city: String,
        state: String,
        postal: String,
        country: String,
        countryIndex : Int,
        email: String,
        areaCode: String,
        phoneNumber: String,
        position: String,
        positionIndex: Int,
        startDate: String?
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.streetAddress1 = streetAddress1
        this.streetAddress2 = streetAddress2
        this.city = city
        this.countryIndex = countryIndex
        this.state = state
        this.postal = postal
        this.country = country
        this.email = email
        this.areaCode = areaCode
        this.phoneNumber = phoneNumber
        this.position = position
        this.positionIndex = positionIndex
        this.startDate = startDate
    }

    override fun toString(): String {
        return "JobApplication{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", streetAddress1='" + streetAddress1 + '\'' +
                ", streetAddress2='" + streetAddress2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postal='" + postal + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", position='" + position + '\'' +
                ", startDate='" + startDate + '\'' +
                '}'
    }
}
