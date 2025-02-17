package kr.project.yuju.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.yuju.mappers.NoticeMapper;
import kr.project.yuju.models.Notice;
import kr.project.yuju.services.NoticeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 공지사항 데이터를 저장한다.
     */
    @Override
    public Notice addItem(Notice input) throws Exception {
        int rows = 0;

        try {
            rows = noticeMapper.insert(input);

            if (rows == 0) {
                throw new Exception("저장된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return noticeMapper.selectItem(input);
    }

    /**
     * 공지사항 데이터를 수정한다.
     */
    @Override
    public Notice editItem(Notice input) throws Exception {
        int rows = 0;

        try {
            rows = noticeMapper.update(input);

            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return noticeMapper.selectItem(input);
    }

    /**
     * 공지사항 데이터를 삭제한다.
     */
    @Override
    public int deleteItem(Notice input) throws Exception {
        int rows = 0;

        try {
            rows = noticeMapper.delete(input);

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
     * 공지사항 데이터를 단일 조회한다.
     */
    @Override
    public Notice getItem(Notice input) throws Exception {
        Notice output = null;

        try {
            output = noticeMapper.selectItem(input);

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
     * 공지사항 데이터를 목록 조회한다.
     */
    @Override
    public List<Notice> getList(Notice input) throws Exception {
        List<Notice> output = null;

        try {
            output = noticeMapper.selectList(input);
        } catch (Exception e) {
            log.error("데이터 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    /**
     * 공지사항 데이터를 수를 조회한다.
     */
    @Override
    public int getCount(Notice input) throws Exception {
        int output = 0;

        try {
            output = noticeMapper.selectCount(input);
        } catch (Exception e) {
            log.error("데이터 집계에 실패했습니다.", e);
            throw e;
        }

        return output;
    }
}