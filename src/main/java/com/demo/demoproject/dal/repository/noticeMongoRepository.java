package com.demo.demoproject.dal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.demoproject.dal.model.NoticeModel;

@Repository
public interface noticeMongoRepository extends MongoRepository<NoticeModel , String>{
    void deleteNoticeModelByPostID(String noticeid);
    NoticeModel findNoticeModelByPostID(String noticeid);
}
