package com.xm.impl;

import com.xm.BugService;
import com.xm.entity.Bugs;
import com.xm.mapper.BugsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("bugService")
public class BugServiceImpl implements BugService {
    @Resource
    private BugsMapper bugsMapper;
    public void insertBug(){
        Bugs bugs=new Bugs();
        bugs.setStatus("2");
        bugsMapper.insert(bugs);
    }
}
