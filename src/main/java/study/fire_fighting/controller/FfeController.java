package study.fire_fighting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import study.fire_fighting.pojo.Ffe;
import study.fire_fighting.pojo.User;
import study.fire_fighting.service.FfeService;
import study.fire_fighting.service.WarerecordsService;
import study.fire_fighting.utils.response.ResponseData;

import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ksw
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/ffe")
public class FfeController {
    @Autowired
    private FfeService ffeService;
    @RequestMapping("/detail")
    public ResponseData getFfe(int eid) {
        QueryWrapper<Ffe> wrapper = new QueryWrapper();
        ResponseData res = new ResponseData();
        wrapper.eq("eid",eid);
        res.put("ffe",ffeService.getOne(wrapper));
        return res;
    }

}

