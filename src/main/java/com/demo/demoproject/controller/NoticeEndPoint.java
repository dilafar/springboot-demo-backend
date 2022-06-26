package com.demo.demoproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
@RequestMapping("/notice")
public class NoticeEndPoint {

    private final NoticeApi noticeApi;

    @Autowired
    public NoticeEndPoint(NoticeApi noticeApi) {
        this.noticeApi = noticeApi;
    }

    @GetMapping
    public ResponseEntity<?> getAllNotice(){
        try{
            List<Notice> notices = noticeApi.getAll();
            if(notices.size()>0){
                return new ResponseEntity<List<Notice>>(notices, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No Notices Available", HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> AddNotice(@RequestBody noticeDto noticeDto){
        try{
            Notice notice = new Notice();
            notice.setDate(noticeDto.getDate());
            notice.setDescription(noticeDto.getDescription());
            notice.setTitle(noticeDto.getTitle());

            Notice notice2 = noticeApi.AddNotice(notice);
            return new ResponseEntity<Notice>(notice2 , HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{postID}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> EdiNotice(@PathVariable String postID , @RequestBody noticeDto noticeDto){
        try{
            Notice notice = new Notice();
            notice.setPostID(postID);
            notice.setDescription(noticeDto.getDescription());
            notice.setDate(noticeDto.getDate());
            notice.setTitle(noticeDto.getTitle());
            
            Notice notice2 =  noticeApi.ediNotice(notice);
            return new ResponseEntity<Notice>(notice2, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
     
    }

    @DeleteMapping("/{postID}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> DeleteNotice(@PathVariable String postID){
        try{
            noticeApi.deleteNotice(postID);
            return new ResponseEntity<>("Notice Deleted Successfully", HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
            
    }

    @GetMapping("/{postID}")
    public ResponseEntity<?> getSingleNotice(@PathVariable String postID){
        try{
            Notice notice = noticeApi.getSingle(postID);
            return new ResponseEntity<Notice>(notice , HttpStatus.OK);
            
        }catch(Exception e){
            return new ResponseEntity<>("Notice Not available Or Invalied NoticeId" , HttpStatus.BAD_REQUEST);
        }

    }

    
}
