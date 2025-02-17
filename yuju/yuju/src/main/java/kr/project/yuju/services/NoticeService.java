package kr.project.yuju.services;

import java.util.List;

import kr.project.yuju.models.Notice;

public interface NoticeService {
    /**
     * 공지사항 데이터를 저장한다.
     * @param params
     * @return
     * @throws Exception
     */
    public Notice addItem(Notice params) throws Exception;

    /**
     * 공지사항 데이터를 수정한다.
     * @param params
     * @return
     * @throws Exception
     */
    public Notice editItem(Notice params) throws Exception;

    /**
     * 공지사항 데이터를 삭제한다.
     * @param params
     * @return
     * @throws Exception
     */
    public int deleteItem(Notice params) throws Exception;

    /**
     * 공지사항 데이터를 단일 조회한다.
     * @param params
     * @return
     * @throws Exception
     */
    public Notice getItem(Notice params) throws Exception;

    /**
     * 공지사항 데이터를 목록 조회한다.
     * @param params
     * @return
     * @throws Exception
     */
    public List<Notice> getList(Notice params) throws Exception;

    /**
     * 공지사항 데이터를 수를 조회한다.
     * @param params
     * @return
     * @throws Exception
     */
    public int getCount(Notice params) throws Exception;
}
