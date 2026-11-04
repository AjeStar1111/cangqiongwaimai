package com.sky.service;

import com.sky.entity.DishFlavor;

import java.util.List;

public interface DishFlavorService {

    /**
     * 批量保存口味数据
     * @param flavors
     */
    void saveBatch(List<DishFlavor> flavors);
}
