package com.demo.demoproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demoproject.domain.Notice;
import com.demo.demoproject.dto.noticeDto;
import com.demo.demoproject.service.NoticeApi;

@RestController
@RequestMapping("/notice")
public class NoticeEndPoint {

    private final NoticeApi noticeApi;

    @Autowired
    public NoticeEndPoint(NoticeApi noticeApi) {
        this.noticeApi = noticeApi;
    }

    @GetMapping
    public List<Notice> getAllNotice(){
        return noticeApi.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notice AddNotice(@RequestBody noticeDto noticeDto){
        Notice notice = new Notice();
        notice.setDate(noticeDto.getDate());
        notice.setDescription(noticeDto.getDescription());
        notice.setTitle(noticeDto.getTitle());

        return noticeApi.AddNotice(notice);
    }

    @PutMapping("/{postID}")
    @ResponseStatus(HttpStatus.CREATED)
    public Notice EdiNotice(@PathVariable String postID , @RequestBody noticeDto noticeDto){
        Notice notice = new Notice();
        notice.setPostID(postID);
        notice.setDescription(noticeDto.getDescription());
        notice.setDate(noticeDto.getDate());
        notice.setTitle(noticeDto.getTitle());

        return noticeApi.ediNotice(notice);
    }

    @DeleteMapping("/{postID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteNotice(@PathVariable String postID){
            noticeApi.deleteNotice(postID);
    }

    
}
