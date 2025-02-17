package kr.project.yuju.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.yuju.mappers.InquiryMapper;
import kr.project.yuju.models.Inquiry;
import kr.project.yuju.services.InquiryService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private InquiryMapper inquiryMapper;

    @Override
    public Inquiry addItem(Inquiry input) throws Exception {
        int rows = 0;

        try {
            rows = inquiryMapper.insert(input);

            if (rows == 0) {
                throw new Exception("저장된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return inquiryMapper.selectItem(input);
    }

    @Override
    public Inquiry editItem(Inquiry input) throws Exception {
        int rows = 0;

        try {
            rows = inquiryMapper.update(input);

            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return inquiryMapper.selectItem(input);
    }

    @Override
    public int deleteItem(Inquiry input) throws Exception {
        int rows = 0;

        try {
            rows = inquiryMapper.delete(input);

            if (rows == 0) {
                throw new Exception("삭제된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 삭제에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }

    @Override
    public Inquiry getItem(Inquiry input) throws Exception {
        Inquiry output = null;

        try {
            output = inquiryMapper.selectItem(input);

            if (output == null) {
                throw new Exception("조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public List<Inquiry> getList(Inquiry input) throws Exception {
        List<Inquiry> output = null;

        try {
            output = inquiryMapper.selectList(input);
        } catch (Exception e) {
            log.error("데이터 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public int getCount(Inquiry input) throws Exception {
        int output = 0;

        try {
            output = inquiryMapper.selectCount(input);
        } catch (Exception e) {
            log.error("데이터 집계에 실패했습니다.", e);
            throw e;
        }

        return output;
    }
}