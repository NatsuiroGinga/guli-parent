package com.atguigu.service_edu.service;

import com.atguigu.common_utils.Result;
import com.atguigu.service_edu.pojo.EduSubject;
import com.atguigu.service_edu.vo.param.ExcelSubjectParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 17400
 * @description 针对表【edu_subject(课程科目)】的数据库操作Service
 * @createDate 2023-01-06 16:06:47
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 批量导入课程分类
     *
     * @param excelSubjectParamList ExcelSubjectParam对象列表
     */
    void saveBatch(List<ExcelSubjectParam> excelSubjectParamList);

    /**
     * 查询所有课程分类
     *
     * @return 所有课程分类
     */
    Result listAll();

}
