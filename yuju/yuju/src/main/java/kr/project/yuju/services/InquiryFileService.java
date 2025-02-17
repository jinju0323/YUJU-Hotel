package kr.project.yuju.services;

import java.util.List;

import kr.project.yuju.models.InquiryFile;

public interface InquiryFileService {
    public InquiryFile addItem(InquiryFile params) throws Exception;

    public InquiryFile editItem(InquiryFile params) throws Exception;

    public int deleteItem(InquiryFile params) throws Exception;

    public InquiryFile getItem(InquiryFile params) throws Exception;

    public List<InquiryFile> getList(InquiryFile params) throws Exception;

    public int getCount(InquiryFile params) throws Exception;
}
