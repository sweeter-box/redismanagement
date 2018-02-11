package com.biz.util;


import java.util.List;

public class Pageutil<E> {  
	    private int pagenum = 1;  //当前页	  
	    private int rows = 10; //显示多少行   	   
	    private int total;  //总记录条数
	    private int pagesum;  //总页数
	    
	    public int getPagenum() {
			return pagenum;
		}

		public void setPagenum(int pagenum) {
			this.pagenum = pagenum;
		}  
	  
	  
		public int getRows() {
			return rows;
		}

		public void setRows(int rows) {
			this.rows = rows;
		}

		public int getTotal() {
			return total;
		}
		
		//返回页数
		public int getPagesum() {
			return (total%rows==0)?(total/rows):(total/rows+1);
		}

		
		/**  
	     * 对list集合进行分页处理  
	     *   
	     * @return  
	     */  
	    public List<E> ListSplit(List<E> list,int pagenum) {  
	        List<E> newList=null;  
	        total=list.size();
	        if(total<1)
	        	return list;
	        pagesum=(total%rows==0)?(total/rows):(total/rows+1);
	        if(pagenum<1)
				pagenum=1;
			if(pagenum>pagesum)
				pagenum=pagesum;
	        newList=list.subList(rows*(pagenum-1), ((rows*pagenum)>total?total:(rows*pagenum)));  
	        return newList;  
	    }

		
	}