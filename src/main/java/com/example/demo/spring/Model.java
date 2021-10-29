package com.example.demo.spring;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NotNull
public class Model {
    @NotNull(message = "姓名不能为空")
    private String name;

    @Min(value = 10, message = "年龄必须大于10岁")
    private int age;

    @NotNull(message = "nums不能为空")
    @Size(min = 1, message = "最少要有1个")
    private List<Integer> nums;
}
