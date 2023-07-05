package cn.daenx.myadmin.test.service.impl;

import cn.daenx.myadmin.common.exception.MyException;
import cn.daenx.myadmin.test.domain.vo.TestPeopleAddVo;
import cn.daenx.myadmin.test.domain.vo.TestPeoplePageVo;
import cn.daenx.myadmin.test.domain.vo.TestPeopleUpdVo;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.daenx.myadmin.test.domain.po.TestPeople;
import cn.daenx.myadmin.test.mapper.TestPeopleMapper;
import cn.daenx.myadmin.test.service.TestPeopleService;

import java.util.List;

@Service
public class TestPeopleServiceImpl extends ServiceImpl<TestPeopleMapper, TestPeople> implements TestPeopleService {
    @Resource
    private TestPeopleMapper testPeopleMapper;

    private LambdaQueryWrapper<TestPeople> getWrapper(TestPeoplePageVo vo) {
        LambdaQueryWrapper<TestPeople> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(vo.getName()), TestPeople::getName, vo.getName());
        wrapper.eq(ObjectUtil.isNotEmpty(vo.getAge()), TestPeople::getAge, vo.getAge());
        wrapper.eq(ObjectUtil.isNotEmpty(vo.getSex()), TestPeople::getSex, vo.getSex());
        String startTime = vo.getStartTime();
        String endTime = vo.getEndTime();
        wrapper.between(ObjectUtil.isNotEmpty(startTime) && ObjectUtil.isNotEmpty(endTime), TestPeople::getCreateTime, startTime, endTime);
        return wrapper;
    }

    private QueryWrapper<TestPeople> getWrapperQuery(TestPeoplePageVo vo) {
        QueryWrapper<TestPeople> wrapper = new QueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(vo.getName()), "tp.name", vo.getName());
        wrapper.eq(ObjectUtil.isNotEmpty(vo.getAge()), "tp.age", vo.getAge());
        wrapper.eq(ObjectUtil.isNotEmpty(vo.getSex()), "tp.sex", vo.getSex());
        String startTime = vo.getStartTime();
        String endTime = vo.getEndTime();
        wrapper.between(ObjectUtil.isNotEmpty(startTime) && ObjectUtil.isNotEmpty(endTime), "tp.create_time", startTime, endTime);
        wrapper.eq("td.is_delete", 0);
        return wrapper;
    }

    /**
     * 测试数据-分页列表_MP分页插件
     *
     * @param vo
     * @return
     */
    @Override
    public IPage<TestPeople> getPage1(TestPeoplePageVo vo) {
        LambdaQueryWrapper<TestPeople> wrapper = getWrapper(vo);
        Page<TestPeople> testDataPage = testPeopleMapper.selectPage(vo.getPage(true), wrapper);
        return testDataPage;
    }

    /**
     * 测试数据-分页列表_自己写的SQL
     *
     * @param vo
     * @return
     */
    @Override
    public IPage<TestPeople> getPage2(TestPeoplePageVo vo) {
        Page<TestPeople> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        IPage<TestPeople> iPage = testPeopleMapper.getPage(page, vo);
        return iPage;
    }

    /**
     * 测试数据-分页列表_MP自定义SQL
     *
     * @param vo
     * @return
     */
    @Override
    public IPage<TestPeople> getPage3(TestPeoplePageVo vo) {
        QueryWrapper<TestPeople> wrapperQuery = getWrapperQuery(vo);
        IPage<TestPeople> iPage = testPeopleMapper.getPageWrapper(vo.getPage(true), wrapperQuery);
        return iPage;
    }

    /**
     * 测试数据-全部数据，不分页
     *
     * @param vo
     * @return
     */
    @Override
    public List<TestPeople> getPage4(TestPeoplePageVo vo) {
        LambdaQueryWrapper<TestPeople> wrapper = getWrapper(vo);
        List<TestPeople> list = testPeopleMapper.selectList(wrapper);
        return list;
    }

    /**
     * 测试数据-新增
     *
     * @param vo
     */
    @Override
    public void addInfo(TestPeopleAddVo vo) {
        TestPeople testPeople = new TestPeople();
        testPeople.setName(vo.getName());
        testPeople.setAge(vo.getAge());
        testPeople.setSex(vo.getSex());
        int insert = testPeopleMapper.insert(testPeople);
        if (insert < 1) {
            throw new MyException("新增失败");
        }
    }

    /**
     * 测试数据-查询
     *
     * @param id
     * @return
     */
    @Override
    public TestPeople getInfo(String id) {
        TestPeople testPeople = testPeopleMapper.selectById(id);
        return testPeople;
    }

    /**
     * 测试数据-修改
     *
     * @param vo
     */
    @Override
    public void editInfo(TestPeopleUpdVo vo) {
        LambdaUpdateWrapper<TestPeople> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(TestPeople::getId, vo.getId());
        updateWrapper.set(TestPeople::getName, vo.getName());
        updateWrapper.set(TestPeople::getAge, vo.getAge());
        updateWrapper.set(TestPeople::getSex, vo.getSex());
        int rows = testPeopleMapper.update(new TestPeople(), updateWrapper);
        if (rows < 1) {
            throw new MyException("修改失败");
        }
    }

    /**
     * 测试数据-删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(List<String> ids) {
        int i = testPeopleMapper.deleteBatchIds(ids);
        if (i < 1) {
            throw new MyException("删除失败");
        }
    }
}
