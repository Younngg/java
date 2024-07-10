package org.zerock.security.service;

import org.zerock.security.dto.PageRequestDTO;
import org.zerock.security.dto.PageResponseDTO;
import org.zerock.security.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long rno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

}
