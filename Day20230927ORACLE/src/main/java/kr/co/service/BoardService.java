package kr.co.service;

import java.util.List;

import kr.co.domain.BoardVO;

public interface BoardService {
	public abstract void register(BoardVO board);
	public abstract BoardVO get(Long bno); //get함수
	public abstract boolean modify(BoardVO board);
	public abstract boolean remove(Long bno); //삭제여부 T/F
	public abstract List<BoardVO> getList();
}
