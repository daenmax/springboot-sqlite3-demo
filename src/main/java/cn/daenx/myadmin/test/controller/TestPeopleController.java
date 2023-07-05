package cn.daenx.myadmin.test.controller;


import cn.daenx.myadmin.common.exception.MyException;
import cn.daenx.myadmin.common.vo.Result;
import cn.daenx.myadmin.test.domain.po.TestPeople;
import cn.daenx.myadmin.test.domain.vo.TestPeopleAddVo;
import cn.daenx.myadmin.test.domain.vo.TestPeoplePageVo;
import cn.daenx.myadmin.test.domain.vo.TestPeopleUpdVo;
import cn.daenx.myadmin.test.service.TestPeopleService;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/test/people")
@Slf4j
public class TestPeopleController {
    @Resource
    private TestPeopleService testPeopleService;

    /**
     * 测试数据-分页列表_MP分页插件
     *
     * @param vo
     * @return
     */
    @GetMapping("/list1")
    public Result list1(TestPeoplePageVo vo) {
        IPage<TestPeople> page = testPeopleService.getPage1(vo);
        return Result.ok(page);
    }

    /**
     * 测试数据-分页列表_自己写的SQL
     *
     * @param vo
     * @return
     */
    @GetMapping("/list2")
    public Result list2(TestPeoplePageVo vo) {
        IPage<TestPeople> page = testPeopleService.getPage2(vo);
        return Result.ok(page);
    }

    /**
     * 测试数据-分页列表_MP自定义SQL
     *
     * @param vo
     * @return
     */
    @GetMapping("/list3")
    public Result list3(TestPeoplePageVo vo) {
        IPage<TestPeople> page = testPeopleService.getPage3(vo);
        return Result.ok(page);
    }

    /**
     * 测试数据-全部数据，不分页
     *
     * @param vo
     * @return
     */
    @GetMapping("/list4")
    public Result list4(TestPeoplePageVo vo) {
        List<TestPeople> list = testPeopleService.getPage4(vo);
        return Result.ok(list);
    }

    /**
     * 测试数据-新增
     *
     * @param vo
     * @return
     */
    @PostMapping
    public Result add(@Validated @RequestBody TestPeopleAddVo vo) {
        testPeopleService.addInfo(vo);
        return Result.ok();
    }

    /**
     * 测试数据-查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result query(@PathVariable String id) {
        TestPeople testPeople = testPeopleService.getInfo(id);
        return Result.ok(testPeople);
    }

    /**
     * 测试数据-修改
     *
     * @param vo
     * @return
     */
    @PutMapping
    public Result edit(@Validated @RequestBody TestPeopleUpdVo vo) {
        testPeopleService.editInfo(vo);
        return Result.ok();
    }

    /**
     * 测试数据-删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping()
    public Result remove(@RequestBody List<String> ids) {
        if (CollUtil.isEmpty(ids)) {
            throw new MyException("参数错误");
        }
        testPeopleService.deleteByIds(ids);
        return Result.ok();
    }


}
