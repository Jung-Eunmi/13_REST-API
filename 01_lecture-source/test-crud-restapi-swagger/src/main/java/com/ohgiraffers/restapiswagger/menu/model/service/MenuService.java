package com.ohgiraffers.restapiswagger.menu.model.service;

import com.ohgiraffers.restapiswagger.menu.model.dao.MenuRepository;
import com.ohgiraffers.restapiswagger.menu.model.dto.MenuDTO;
import com.ohgiraffers.restapiswagger.menu.model.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository repository;
    private final ModelMapper modelMapper;

    public List<MenuDTO> findAllMenu() {

        List<Menu> menuList = repository.findAll();

        return menuList.stream().map(menu -> modelMapper.map(menu, MenuDTO.class)).collect(Collectors.toList());
    }

    public MenuDTO findMenuByCode(int menuCode) {

        Menu menu = repository.findById(menuCode).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(menu, MenuDTO.class);
    }

    @Transactional
    public void registMenu(MenuDTO newMenu) {

        repository.save(modelMapper.map(newMenu, Menu.class));
    }

    @Transactional
    public void deleteMenuByCode(int menuCode) {
        repository.deleteById(menuCode);
    }

    @Transactional
    public void modifyMenu(MenuDTO modifyMenu) {
        Menu foundMenu = repository.findById(modifyMenu.getMenuCode()).orElseThrow(IllegalArgumentException::new);

        foundMenu = foundMenu.toBuilder()
                             .menuName(modifyMenu.getMenuName())
                             .menuPrice(modifyMenu.getMenuPrice())
                             .build();

        repository.save(foundMenu);
    }
}
