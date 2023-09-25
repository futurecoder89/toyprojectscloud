package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ToyVO;

public class ToyDAO {

	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//장바구니 목록 보기
	public List<ToyVO> selectList() {
		List<ToyVO> list = sqlSession.selectList("t.toy_cartlist");
		return list;
	
	//장바구니 상품 담기
	public int addCart(ToyVO vo) {
		
		int res = sqlSession.insert("t.toy_addtocart", vo);
		return res;
	}
	//장바구니 상품 삭제
	public int deleteProduct(int id) {
		int res = sqlSession.delete("t.toy_delete", id);
		return res;
	}
	//주문하기
	
	public int orderProduct(ToyVO vo) {
		int res = sqlSession.insert("t.toy_orderproduct", vo)
				return res;
	}
	
	//주문 목록 보기
	public orderList<OrderVO> selectOrderList() {
		List<OrderVO> orderlist = sqlSession.selectList("t.toy_orderlist");
		return orderlist;
		
	}
	
	
}
}