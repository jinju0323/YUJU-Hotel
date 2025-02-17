package kr.project.yuju.services;

import java.util.List;

import kr.project.yuju.models.InquiryFile;

public interface InquiryFileService {
    /**
     * 문의 파일 데이터를 저장한다.
     * @param params
     * @return
     * @throws Exception
     */
    public InquiryFile addItem(InquiryFile params) throws Exception;

    /**
     * 문의 파일 데이터를 수정한다.
     * @param params
     * @return
     * @throws Exception
     */
    public InquiryFile editItem(InquiryFile params) throws Exception;

    /**
     * 문의 파일 데이터를 삭제한다.
     * @param params
     * @return
     * @throws Exception
     */
    public int deleteItem(InquiryFile params) throws Exception;

    /**
     * 문의 파일 데이터를 단일 조회한다.
     * @param params
     * @return
     * @throws Exception
     */
    public InquiryFile getItem(InquiryFile params) throws Exception;

    /**
     * 문의 파일 데이터를 목록 조회한다.
     * @param params
     * @return
     * @throws Exception
     */
    public List<InquiryFile> getList(InquiryFile params) throws Exception;

    /**
     * 문의 파일 데이터를 수를 조회한다.
     * @param params
     * @return
     * @throws Exception
     */
    public int getCount(InquiryFile params) throws Exception;
}
