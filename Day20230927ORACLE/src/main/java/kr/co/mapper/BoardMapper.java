package kr.co.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import kr.co.domain.BoardVO;

public interface BoardMapper { //DAO

//	@Select("select * from tbl_board where bno > 0") BoardMapper.xml에 빼놨으니 빼도 됨
	public abstract List<BoardVO> getList();
	public abstract void insert(BoardVO board); // 둘다 등록이지만 얘는 기본키를 모를때 
	public abstract void insertSelectKey(BoardVO board); //이게 primary key이다 얘는 기본키를 알때
	public abstract BoardVO read(Long bno);
	public abstract int delete(Long bno);
	public abstract int update(BoardVO board);
}
