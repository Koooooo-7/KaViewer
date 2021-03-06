package com.koy.kaviewer.kafka.ipc;

import com.koy.kaviewer.kafka.entity.TopicMetaVO;

import java.util.List;
import java.util.Set;

public interface TopicService {
    Set<String> list(String clusterName);


    List<TopicMetaVO> listMeta(String clusterName);
}
