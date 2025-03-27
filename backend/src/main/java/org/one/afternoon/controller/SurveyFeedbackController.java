package org.one.afternoon.controller;


import org.one.afternoon.common.Result;
import org.one.afternoon.domain.SurveyFeedbackRecord;
import org.one.afternoon.domain.SurveyFeedback;
import org.one.afternoon.domain.SurveyFeedbackRecord;
import org.one.afternoon.domain.Teacher;
import org.one.afternoon.mapper.SurveyFeedbackRecordMapper;
import org.one.afternoon.service.SurveyFeedbackRecordService;
import org.one.afternoon.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.one.afternoon.common.Result.success;

/**
 * 响应学生表
 */
@RestController
@RequestMapping(value = "/surveyFeedback")
public class SurveyFeedbackController {
    @Autowired
    private SurveyFeedbackRecordService surveyFeedbackRecordService;
    @Autowired
    private RedisService redisService;
    @Autowired
    SurveyFeedbackRecordMapper surveyFeedbackMapper;

    @CrossOrigin // 跨域访问
    @GetMapping(value = "/all")
    @ResponseBody
    public Result<List<SurveyFeedback>> getAll() {
        List<SurveyFeedback> all = surveyFeedbackRecordService.findAll();
        return success(all);
    }
    @CrossOrigin // 跨域访问
    @PostMapping(value = "/queryOne")
    @ResponseBody
    public Result<List<SurveyFeedback>> queryOne(@RequestBody SurveyFeedback surveyFeedback) {
        List<SurveyFeedback> all = surveyFeedbackRecordService.queryOne(surveyFeedback);
        return success(all);
    }
    @CrossOrigin // 跨域访问
    @PostMapping(value = "/findOne")
    @ResponseBody
    public Result<SurveyFeedback> findOne(@RequestParam Integer index) {
        SurveyFeedback all = surveyFeedbackRecordService.findById(index);
        return success(all);
    }
    @CrossOrigin
    @PostMapping("/insertOne")
    @ResponseBody
    public Result<String> insert(@RequestBody SurveyFeedbackRecord surveyFeedbackRecord) throws NoSuchAlgorithmException {
        surveyFeedbackRecord.setId(surveyFeedbackMapper.getLastSurveyFeedbackRecordId()+1);
        boolean save = surveyFeedbackRecordService.addSurveyFeedbackRecord(surveyFeedbackRecord);
        redisService.setValue("surveyFeedbacks",null);
        if (save) {
            return Result.success("成功添加用户");
        }
        return Result.error("添加用户失败");
    }
    @CrossOrigin
    @PostMapping("/updateOne")
    @ResponseBody
    public Result<String> update(@RequestBody SurveyFeedbackRecord surveyFeedbackRecordRecord) throws NoSuchAlgorithmException {
        boolean save = surveyFeedbackRecordService.update(surveyFeedbackRecordRecord);
        redisService.setValue("surveyFeedbackRecords",null);
        if (save) {
            return Result.success("成功更新状态");
        }
        return Result.error("更新状态失败");
    }
    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> deleteOne(@RequestParam Integer index) {
        boolean save=surveyFeedbackRecordService.deleteById(index);
        if(save){
            return Result.success("删除成功");}
        else {
            return Result.error("删除失败");
        }
    }
    @CrossOrigin
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            surveyFeedbackRecordService.deleteById(id);
        }
        return Result.success("批量删除成功");
    }
}
