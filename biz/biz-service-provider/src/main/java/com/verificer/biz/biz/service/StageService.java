package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.StageVo;
import com.verificer.biz.beans.vo.req.StageFormVo;
import com.verificer.biz.beans.vo.req.StagePageVo;
import com.verificer.biz.biz.entity.Stage;

import java.util.List;

public interface StageService {
    Stage getById(Long stageId);

    /**
     * 仓库列表
     * @return
     */
    List<StageVo> stageList();

    /**
     * 仓库列表（分页）
     * @param qryVo
     * @return
     */
    List<StageVo> stagePage(StagePageVo qryVo);

    /**
     * 统计符合条件的仓库数
     * @param qryVo
     * @return
     */
    int stageCount(StagePageVo qryVo);

    /**
     * 新增仓库
     * @param formVo
     */
    void stageAdd(StageFormVo formVo);

    /**
     * 修改仓库
     * @param formVo
     */
    void stageUpd(StageFormVo formVo);
}
