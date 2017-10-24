package nl.yacht.lakesideresort.controller;

import nl.yacht.lakesideresort.domain.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {


}