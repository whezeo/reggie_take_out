package com.cuit.reggie.vo.dto;

import com.cuit.reggie.pojo.Setmeal;
import com.cuit.reggie.pojo.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
