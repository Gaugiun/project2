package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallStorage;

import java.util.List;

public interface SystemStorageService {
    List<CskaoyanMallStorage> selectStorage(Integer key, String name);

}
