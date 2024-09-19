package com.zaurtregulov.spring.boot.jpa.service;

import com.zaurtregulov.spring.boot.jpa.entity.Application;
import com.zaurtregulov.spring.boot.jpa.entity.MyUser;

import java.util.List;

public interface AppService {
    public void loadAppInDB();
    public List<Application> getApplicationList();
    public Application getApplicationById(int id);
    public void addUser(MyUser user);
}
