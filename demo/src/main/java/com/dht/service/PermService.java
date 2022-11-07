package com.dht.service;

import com.dht.pojo.PermVO;

import java.util.List;

public interface PermService {
    PermVO getPerm(int id) throws Exception;

    List<PermVO> getPermList() throws Exception;
}
