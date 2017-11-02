package nl.yacht.lakesideresort.repository;

import nl.yacht.lakesideresort.domain.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {

    List<Guest> findBySurNameAndFirstNameAndAddressAndPostalCodeAndPhoneNumberAndMailAddress(String surName, String firstName, String address, String postalCode, String phoneNumber, String mailAddress);
}