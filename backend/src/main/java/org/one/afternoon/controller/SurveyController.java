package org.one.afternoon.controller;


import org.one.afternoon.common.Result;
import org.one.afternoon.domain.Survey;
import org.one.afternoon.service.SurveyService;
import org.one.afternoon.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 响应用户
 */
@RestController
@RequestMapping(value = "/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;
    @Autowired
    private RedisService redisService;

    @Autowired

    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Result<List<Survey>> findAllSurvey() {
//        List<Survey> surveys=(List<Survey>) redisService.getValue("surveys");
        List<Survey> surveys = surveyService.findAll();
//        if (surveys==null) {
//            surveys = surveyService.findAll();
//            redisService.setValue("surveys",surveys);
//        }
        return Result.success(surveys);
    }


    //复合查询
    @CrossOrigin
    @PostMapping("/queryOne")
    @ResponseBody
    public Result<List<Survey>> querySurvey(@RequestBody Survey survey) {
        return Result.success(surveyService.queryOne(survey));
    }
    @CrossOrigin
    @PostMapping("/findOne")
    @ResponseBody
    public Result<Survey> findSurveyById(@RequestParam int index) {
        return Result.success(surveyService.findSurveyById(index));
    }

    @CrossOrigin
    @PostMapping("/insertOne")
    @ResponseBody
    public Result<String> insertOne(@RequestBody Survey survey) {
        survey.setId(surveyService.getLasId()+1);
        boolean save = surveyService.insert(survey);
        redisService.setValue("surveys",null);
        if (save) {
            return Result.success("成功添加");
        }
        return Result.error("添加失败");
    }

    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> deleteOne(@RequestParam Integer index) {
        surveyService.deleteById(index);
        return Result.success("删除成功");
    }


    @CrossOrigin
    @PostMapping("/updateOne")
    @ResponseBody
    public Result<String> updateOne(@RequestBody Survey survey) {
        boolean update = surveyService.update(survey);
        if (update) {
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }
}
