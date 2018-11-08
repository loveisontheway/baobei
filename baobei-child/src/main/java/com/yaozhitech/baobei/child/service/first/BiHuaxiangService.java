package com.yaozhitech.baobei.child.service.first;
import com.yaozhitech.baobei.child.domain.BiHuaxiang;
import java.util.List;
import java.lang.Integer;

/**
 * 用户画像 - Service接口类
 *
 * @author jiangjialiang on 2018/11/06
 */
public interface BiHuaxiangService {

    int deleteByPrimaryKey(Integer id);

    int insert(BiHuaxiang biHuaxiang);

    BiHuaxiang selectByPrimaryKey(Integer id);

    List<BiHuaxiang> selectAll();

    int updateByPrimaryKey(BiHuaxiang biHuaxiang);

}
