package com.yaozhitech.baobei.child.mapper.first;


import com.yaozhitech.baobei.child.domain.ChildArchives;

import java.util.List;

public interface ChildArchivesMapper {

    List<ChildArchives> list(Integer page, Integer size);
}