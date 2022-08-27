package com.keduit.project02.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.keduit.project02.domain.BookingVo;
import com.keduit.project02.domain.Criteria;


@Mapper //mybatis mapper 인터페이스임을 선언
public interface ReserMapper {
 
	

	List<BookingVo> reserList( String username ,  Criteria cri) throws Exception;
	//메소드의 이름과 반환 형식만 지정

	int listCountCriteria(String username , Criteria cri);
	
	
}
