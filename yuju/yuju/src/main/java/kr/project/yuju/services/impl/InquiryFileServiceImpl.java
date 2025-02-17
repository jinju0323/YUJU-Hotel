package kr.project.yuju.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.yuju.mappers.InquiryFileMapper;
import kr.project.yuju.models.InquiryFile;
import kr.project.yuju.services.InquiryFileService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InquiryFileServiceImpl implements InquiryFileService {

    @Autowired
    private InquiryFileMapper inquiryFileMapper;

    /**
     * 문의 파일 데이터를 저장한다.
     */
    @Override
    public InquiryFile addItem(InquiryFile input) throws Exception {
        int rows = 0;

        try {
            rows = inquiryFileMapper.insert(input);

            if (rows == 0) {
                throw new Exception("저장된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return inquiryFileMapper.selectItem(input);
    }

    /**
     * 문의 파일 데이터를 수정한다.
     */
    @Override
    public InquiryFile editItem(InquiryFile input) throws Exception {
        int rows = 0;

        try {
            rows = inquiryFileMapper.update(input);

            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return inquiryFileMapper.selectItem(input);
    }

    /**
     * 문의 파일 데이터를 삭제한다.
     */
    @Override
    public int deleteItem(InquiryFile input) throws Exception {
        int rows = 0;

        try {
            rows = inquiryFileMapper.delete(input);

            if (rows == 0) {
                throw new Exception("삭제된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 삭제에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }

    /**
     * 문의 파일 데이터를 단일 조회한다.
     */
    @Override
    public InquiryFile getItem(InquiryFile input) throws Exception {
        InquiryFile output = null;

        try {
            output = inquiryFileMapper.selectItem(input);

            if (output == null) {
                throw new Exception("조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    /**
     * 문의 파일 데이터를 목록 조회한다.
     */
    @Override
    public List<InquiryFile> getList(InquiryFile input) throws Exception {
        List<InquiryFile> output = null;

        try {
            output = inquiryFileMapper.selectList(input);
        } catch (Exception e) {
            log.error("데이터 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    /**
     * 문의 파일 데이터를 수를 조회한다.
     */
    @Override
    public int getCount(InquiryFile input) throws Exception {
        int output = 0;

        try {
            output = inquiryFileMapper.selectCount(input);
        } catch (Exception e) {
            log.error("데이터 집계에 실패했습니다.", e);
            throw e;
        }

        return output;
    }
}