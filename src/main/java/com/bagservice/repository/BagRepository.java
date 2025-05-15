package com.bagservice.repository;

import com.bagservice.model.Bag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Bag entity
 */
@Repository
public interface BagRepository extends JpaRepository<Bag, Long> {

    /**
     * Find a bag by its tag number
     * @param bagTag the bag tag number
     * @return the bag if found
     */
    Optional<Bag> findByBagTag(String bagTag);
    
    /**
     * Find bags by passenger name
     * @param passengerName the passenger name
     * @return list of bags for the passenger
     */
    List<Bag> findByPassengerName(String passengerName);
    
    /**
     * Find bags by flight details
     * @param flightNumber the flight number
     * @param flightDate the flight date
     * @param airline the airline
     * @return list of bags for the flight
     */
    List<Bag> findByFlightNumberAndFlightDateAndAirline(String flightNumber, LocalDate flightDate, String airline);
    
    /**
     * Find bags by flight details, origin and destination
     * @param flightNumber the flight number
     * @param flightDate the flight date
     * @param airline the airline
     * @param origin the origin
     * @param destination the destination
     * @return list of bags matching the criteria
     */
    List<Bag> findByFlightNumberAndFlightDateAndAirlineAndOriginAndDestination(
            String flightNumber, LocalDate flightDate, String airline, String origin, String destination);
    
    /**
     * Find bags by passenger name and flight details
     * @param passengerName the passenger name
     * @param flightNumber the flight number
     * @param flightDate the flight date
     * @param airline the airline
     * @return list of bags matching the criteria
     */
    List<Bag> findByPassengerNameAndFlightNumberAndFlightDateAndAirline(
            String passengerName, String flightNumber, LocalDate flightDate, String airline);
} 