package kr.project.yuju.services;

import java.util.List;

import kr.project.yuju.models.Notice;

public interface NoticeService {
    public Notice addItem(Notice params) throws Exception;

    public Notice editItem(Notice params) throws Exception;

    public int deleteItem(Notice params) throws Exception;

    public Notice getItem(Notice params) throws Exception;

    public List<Notice> getList(Notice params) throws Exception;

    public int getCount(Notice params) throws Exception;
}
