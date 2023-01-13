package com.atguigu.service_edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.atguigu.common_utils.ErrorInfo;
import com.atguigu.common_utils.Jackson;
import com.atguigu.service_base.exceptionhandler.exception.GuliException;
import com.atguigu.service_edu.service.EduSubjectService;
import com.atguigu.service_edu.vo.param.ExcelSubjectParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author ginga
 * @since 6/1/2023 下午5:16
 */
@Slf4j
@RequiredArgsConstructor
public class ExcelSubjectListener implements ReadListener<ExcelSubjectParam> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private final EduSubjectService eduSubjectService;

    private final Jackson jackson;

    /**
     * 缓存的数据
     */
    private List<ExcelSubjectParam> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context analysis context
     */
    @Override
    public void invoke(ExcelSubjectParam data, AnalysisContext context) {
        if (data == null) {
            throw new GuliException(ErrorInfo.FILE_UPLOAD_EMPTY);
        }
        log.info("解析到一条数据:{}", jackson.bean2Json(data));
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context analysis context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        eduSubjectService.saveBatch(cachedDataList);
        log.info("存储数据库成功！");
    }
}
