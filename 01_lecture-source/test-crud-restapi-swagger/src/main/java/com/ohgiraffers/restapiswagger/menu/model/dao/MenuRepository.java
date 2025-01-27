package com.ohgiraffers.restapiswagger.menu.model.dao;

import com.ohgiraffers.restapiswagger.menu.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
