package cn.daenx.myadmin.test.domain.vo;

import cn.daenx.myadmin.common.vo.BasePageVo;
import lombok.Data;

@Data
public class TestPeoplePageVo extends BasePageVo {
    private String name;
    private Integer age;
    private String sex;
}
