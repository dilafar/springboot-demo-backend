package com.demo.demoproject.dal.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.demo.demoproject.dal.model.NoticeModel;
import com.demo.demoproject.dal.repository.noticeMongoRepository;
import com.demo.demoproject.domain.Notice;
import com.demo.demoproject.domain.NoticeDataAdapter;

@Component
public class NoticeDataAdapterMongoImpl implements NoticeDataAdapter{

    private final noticeMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    public NoticeDataAdapterMongoImpl(noticeMongoRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Notice> getAllNotices() {
       List<NoticeModel> noticeModels = repository.findAll();
       List<Notice> notices = new ArrayList<>();

       for(NoticeModel noticeModel: noticeModels){
           Notice notice = new Notice();
           notice.setPostID(noticeModel.getPostID());
           notice.setDescription(noticeModel.getDescription());
           notice.setTitle(noticeModel.getTitle());
           notice.setDate(noticeModel.getDate());

           notices.add(notice);
       }
        return notices;
    }

    @Override
    public Notice SaveNotice(Notice notice) {
       NoticeModel noticeModel = new NoticeModel();
       noticeModel.setDescription(notice.getDescription());
       noticeModel.setTitle(notice.getTitle());
       noticeModel.setDate(notice.getDate());
       noticeModel = repository.save(noticeModel);
       notice.setPostID(noticeModel.getPostID());
        return notice;
    }

    @Override
    public Notice UpdatNotice(Notice notice) {
        NoticeModel noticeModel = mongoTemplate.findAndModify(Query.query(Criteria.where("postID").is(notice.getPostID())),
        new Update().set("title", notice.getTitle()).set("description", notice.getDescription()).set("date", notice.getDate()), NoticeModel.class);
        
        return notice;
    }

    @Override
    public void DeleteNotice(String noticeid) {
       repository.deleteNoticeModelByPostID(noticeid);
        
    }
    
}
