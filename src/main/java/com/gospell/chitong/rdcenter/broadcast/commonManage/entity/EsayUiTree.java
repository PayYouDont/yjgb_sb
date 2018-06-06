package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class EsayUiTree {
	
	private int id;  
    
    private String text;  
      
    private String iconCls;  
      
    private String url;  
	
    private String state = "";    
    
    private Boolean checked = false;  
    
    private List<EsayUiTree> children = new ArrayList<EsayUiTree>();  
  
}
