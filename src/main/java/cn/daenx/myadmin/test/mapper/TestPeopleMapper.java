package cn.daenx.myadmin.test.mapper;

import cn.daenx.myadmin.test.domain.po.TestPeople;
import cn.daenx.myadmin.test.domain.vo.TestPeoplePageVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestPeopleMapper extends BaseMapper<TestPeople> {

    /**
     * 测试数据分页列表_自己写的SQL
     *
     * @param page
     * @param vo
     * @return
     */
    IPage<TestPeople> getPage(Page<TestPeople> page, @Param("vo") TestPeoplePageVo vo);

    /**
     * 测试数据分页列表_MP自定义SQL
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<TestPeople> getPageWrapper(Page<TestPeople> page, @Param("ew") Wrapper<TestPeople> wrapper);
}
