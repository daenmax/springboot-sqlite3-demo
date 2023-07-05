package cn.daenx.myadmin.test.domain.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TestPeopleUpdVo {
    @NotBlank(message = "ID不能为空")
    private String id;
    private String name;
    private Integer age;
    private String sex;
}
