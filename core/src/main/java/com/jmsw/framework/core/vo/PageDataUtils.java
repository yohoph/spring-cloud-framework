package com.jmsw.framework.core.vo;

import com.github.pagehelper.PageInfo;

public class PageDataUtils {
	@SuppressWarnings("unchecked")
	public static <T,F> PageData<T,F>  toPageData(PageInfo<T> page,PageRequest<F> pageReq){
		PageData<T,F> pageData=new PageData<T,F>();
		pageData.setEntities(page.getList());
		PageRequest<F> nextPage;
		if(pageReq.getOffset()==0){
			nextPage=pageReq.buildNextPage(page.getTotal());
		}else{
			nextPage=pageReq.buildNextPage();
		}
		pageData.setNext(nextPage);
		return pageData;
	}
}
