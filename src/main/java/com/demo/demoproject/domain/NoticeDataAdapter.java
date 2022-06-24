package com.demo.demoproject.domain;

import java.util.List;

public interface NoticeDataAdapter {
    List<Notice> getAllNotices();
    Notice SaveNotice(Notice notice);
    Notice UpdatNotice(Notice notice);
    void DeleteNotice(String noticeid);
}
