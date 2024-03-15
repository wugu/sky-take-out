package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
public class DishServiceImpl implements DishService{

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    /**
     * 新增菜品和口味数据
     * @param dishDTO
     */
    @Transactional//事务注解，同成功同失败，对于多个表进行操作
    public void saveWithFlavor(DishDTO dishDTO) {

        Dish dish = new Dish();
        //属性拷贝
        BeanUtils.copyProperties(dishDTO,dish);

        //向菜品表插入一条数据
        dishMapper.insert(dish);

        //获取insert语句生成的主键值
        Long dishId = dish.getId();

        List<DishFlavor> flavors = dishDTO.getFlavors();
        //提交的口味可以没有
        if (flavors != null && flavors.size() > 0){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            //向口味表插入n条数据
            dishFlavorMapper.insertBath(flavors);
        }
    }
}
