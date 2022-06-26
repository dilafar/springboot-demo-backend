package com.demo.demoproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.demoproject.domain.Notice;
import com.demo.demoproject.domain.NoticeDataAdapter;

@Service
public class NoticeApi {

    private final NoticeDataAdapter noticeDataAdapter;

    @Autowired
    public NoticeApi(NoticeDataAdapter noticeDataAdapter) {
        this.noticeDataAdapter = noticeDataAdapter;
    }

    public  List<Notice> getAll(){
        return noticeDataAdapter.getAllNotices();
    }

    public Notice AddNotice(Notice notice){
        return noticeDataAdapter.SaveNotice(notice);
    }

    public Notice ediNotice(Notice notice){
        return noticeDataAdapter.UpdatNotice(notice);
    }

    public void deleteNotice(String noticeid){
         noticeDataAdapter.DeleteNotice(noticeid);
    }

    public Notice getSingle(String noticeid){
        return noticeDataAdapter.getSingleNotice(noticeid);
    }
}
