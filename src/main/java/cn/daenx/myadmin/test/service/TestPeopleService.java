package cn.daenx.myadmin.test.service;

import cn.daenx.myadmin.test.domain.po.TestPeople;
import cn.daenx.myadmin.test.domain.vo.TestPeopleAddVo;
import cn.daenx.myadmin.test.domain.vo.TestPeoplePageVo;
import cn.daenx.myadmin.test.domain.vo.TestPeopleUpdVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TestPeopleService extends IService<TestPeople> {

    /**
     * 测试数据-分页列表_MP分页插件
     *
     * @param vo
     * @return
     */
    IPage<TestPeople> getPage1(TestPeoplePageVo vo);

    /**
     * 测试数据-分页列表_自己写的SQL
     *
     * @param vo
     * @return
     */
    IPage<TestPeople> getPage2(TestPeoplePageVo vo);

    /**
     * 测试数据-分页列表_MP自定义SQL
     *
     * @param vo
     * @return
     */
    IPage<TestPeople> getPage3(TestPeoplePageVo vo);

    /**
     * 测试数据-全部数据，不分页
     *
     * @param vo
     * @return
     */
    List<TestPeople> getPage4(TestPeoplePageVo vo);

    /**
     * 测试数据-新增
     *
     * @param vo
     */
    void addInfo(TestPeopleAddVo vo);

    /**
     * 测试数据-查询
     *
     * @param id
     * @return
     */
    TestPeople getInfo(String id);

    /**
     * 测试数据-修改
     *
     * @param vo
     */
    void editInfo(TestPeopleUpdVo vo);

    /**
     * 测试数据-删除
     *
     * @param ids
     */
    void deleteByIds(List<String> ids);
}
