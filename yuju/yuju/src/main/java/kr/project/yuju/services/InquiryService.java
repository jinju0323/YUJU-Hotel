package kr.project.yuju.services;

import java.util.List;

import kr.project.yuju.models.Inquiry;

public interface InquiryService {
    /**
     * 문의 데이터를 저장한다.
     * @param params
     * @return
     * @throws Exception
     */
    public Inquiry addItem(Inquiry params) throws Exception;

    /**
     * 문의 데이터를 수정한다.
     * @param params
     * @return
     * @throws Exception
     */
    public Inquiry editItem(Inquiry params) throws Exception;

    /**
     * 문의 데이터를 삭제한다.
     * @param params
     * @return
     * @throws Exception
     */
    public int deleteItem(Inquiry params) throws Exception;

    /**
     * 문의 데이터를 단일 조회한다.
     * @param params
     * @return
     * @throws Exception
     */
    public Inquiry getItem(Inquiry params) throws Exception;

    /**
     * 문의 데이터를 목록 조회한다.
     * @param params
     * @return
     * @throws Exception
     */
    public List<Inquiry> getList(Inquiry params) throws Exception;

    /**
     * 문의 데이터를 수를 조회한다.
     * @param params
     * @return
     * @throws Exception
     */
    public int getCount(Inquiry params) throws Exception;
}
