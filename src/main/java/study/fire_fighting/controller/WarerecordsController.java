package study.fire_fighting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import study.fire_fighting.pojo.Ffe;
import study.fire_fighting.pojo.Warerecords;
import study.fire_fighting.service.FfeService;
import study.fire_fighting.service.WarerecordsService;
import study.fire_fighting.service.impl.FfeServiceImpl;
import study.fire_fighting.utils.response.ResponseData;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ksw
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/warerecords")
public class WarerecordsController {

    @Autowired
    private WarerecordsService warerecordsService;

    @Autowired
    private FfeService ffeService;

    @RequestMapping("/selectList")
    public ResponseData getByWhid(int whid) {
        ResponseData res = new ResponseData();
        List<Warerecords> list;
        QueryWrapper<Warerecords> wrapper = new QueryWrapper<>();
        wrapper.eq("whid",whid);
        list = warerecordsService.list(wrapper);
        res.put("ffeList",getByIDs(list));
        return res;
    }


    public List<Ffe> getByIDs(List<Warerecords> warerecordsList){
        List<Ffe> list = new ArrayList<>();
        for (Warerecords warerecords : warerecordsList) {
            list.add(ffeService.getById(warerecords.getEid()));
        }
        return list;
    }
}

