package com.verificer.utils;




import com.verificer.beans.TreeNodeVo;

import java.util.*;

/**
 * Created by liujinhua on 2017/1/13.
 */
public class TreeUtils {

    /**
     * 构建返回给Tree控件族的数据
     * @param datas 列表数据
     * @param idFieldName id字段名称
     * @param parentIdFieldName parentId字段名称
     * @param rootElemParentIdFieldValue 根元素的parentId值
     * @return
     */
    public static <T>  List<TreeNodeVo<T>> buildTreeDatas(List<T> datas, String idFieldName,
                                                          String parentIdFieldName, Object rootElemParentIdFieldValue){
        if(datas == null || SStringUtils.isAnyEmpty(idFieldName,parentIdFieldName)){
            throw new IllegalArgumentException("参数错误");
        }

        Map<T,Map> objMaps = new LinkedHashMap<T, Map>();
        for(T obj : datas) {
            Map objMap = SBeanUtils.beanToLinkHashMap2(obj);
            if(!objMap.containsKey(idFieldName)
                    || !objMap.containsKey(parentIdFieldName) ){
                throw new IllegalArgumentException("datas数组内的每个元素必须包含属性"+idFieldName+"、"+parentIdFieldName+"");
            }
            if(objMap.get(idFieldName).equals(objMap.get(parentIdFieldName))){
                throw new IllegalArgumentException("datas数组内仲元素的idFiledName值与parentIdFiledName值不能相等");
            }
            objMaps.put(obj,objMap);
        }



        List<TreeNodeVo<T>> result = new ArrayList<>();
        for(T vo : objMaps.keySet()){

            TreeNodeVo<T> treeNodeVo = new TreeNodeVo<T>();
            treeNodeVo.setVo(vo);

            Map<String,Object> objMap = objMaps.get(vo);
            Object parentId = objMap.get(parentIdFieldName);
            Object id = objMap.get(idFieldName);

            if((rootElemParentIdFieldValue == null && parentId == null)
                    || (parentId != null && parentId.equals(rootElemParentIdFieldValue))
                    || (rootElemParentIdFieldValue != null && rootElemParentIdFieldValue.equals(parentId))){
                List<TreeNodeVo<T>> children = buildTreeDatas(datas,idFieldName,parentIdFieldName,id);
                treeNodeVo.setChildren(children);
                result.add(treeNodeVo);
            }
        }
        return result;
    }

    public static  <T> List<Map> parseTreeNodes(List<TreeNodeVo<T>> treeNodeVos){
        List<Map> rst = new LinkedList<>();
        if(treeNodeVos == null || treeNodeVos.size() == 0){
            return new LinkedList<>();
        }

        for(TreeNodeVo<T> treeNode : treeNodeVos){
            Map map = SBeanUtils.beanToLinkHashMap2(treeNode.getVo());
            map.put("children",parseTreeNodes(treeNode.getChildren()));
            rst.add(map);
        }
        return rst;
    }
}
