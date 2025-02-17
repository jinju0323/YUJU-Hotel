package kr.project.yuju.services;

import java.util.List;

import kr.project.yuju.models.Inquiry;

public interface InquiryService {
    public Inquiry addItem(Inquiry params) throws Exception;

    public Inquiry editItem(Inquiry params) throws Exception;

    public int deleteItem(Inquiry params) throws Exception;

    public Inquiry getItem(Inquiry params) throws Exception;

    public List<Inquiry> getList(Inquiry params) throws Exception;

    public int getCount(Inquiry params) throws Exception;
}
