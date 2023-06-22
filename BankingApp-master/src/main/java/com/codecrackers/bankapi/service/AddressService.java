package com.codecrackers.bankapi.service;

import com.codecrackers.bankapi.errorHandler.AddressNotFoundException;
import com.codecrackers.bankapi.exception.ResourceNotFoundException;
import com.codecrackers.bankapi.model.Address;
import com.codecrackers.bankapi.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    private void verifyAddress(Long addressId) throws AddressNotFoundException {
        if (!(this.addressRepository.existsById(addressId))) {
            throw (new ResourceNotFoundException(
                    " code : 404 , " + " Error updating address with id :" + addressId ));
        }
    }
    public Address addAddress(Address address) {
        logger.info("Adding new address: {}", address);
           return addressRepository.save(address);
    }

    public Iterable<Address> getAllAddresses() {
        logger.debug("Retrieving all addresses");
               try { return addressRepository.findAll();
               }catch (Exception e){
         throw new RuntimeException("code : 404 " + ", message : error fetching address");
      }
    }

    public Address getAddressById(Long id) {
        logger.debug("Retrieving address with ID: {}", id);
        return addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("code :" + 404 + ",  " +
                "message  :" + "  error fetching address with id:" + id))
        );

    }

    public Address updateAddress(Long id, Address newAddress) {
        logger.debug("Updating address with ID: {}, New address: {}", id, newAddress);
        verifyAddress(id);
        Address address = addressRepository.findById(id).orElse(new Address());
        address.setStreetNumber(newAddress.getStreetNumber());
        address.setStreetName(newAddress.getStreetName());
        address.setCity(newAddress.getCity());
        address.setState(newAddress.getState());
        address.setZip(newAddress.getZip());
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        logger.warn("Deleting address with ID: {}", id);
        addressRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("code : 404 , message: This id does not exist in addresses")));
       try {
           addressRepository.deleteById(id);
       }catch (Exception e){
           throw new RuntimeException("Fail deleting the address");
       }
    }

}
