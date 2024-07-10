package org.zerock.security.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.security.domain.Board;
import org.zerock.security.dto.BoardListAllDTO;
import org.zerock.security.dto.BoardListReplyCountDTO;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types,
                                                      String keyword,
                                                      Pageable pageable);

    Page<BoardListAllDTO> searchWithAll(String[] types,
                                        String keyword,
                                        Pageable pageable);
}
