package com.shopping.cart.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.cart.app.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
