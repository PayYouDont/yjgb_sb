package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BaseEntity<T> implements Serializable{
	private static final long serialVersionUID = 1L;

	private String text;

    private String state = "closed";

    private Boolean checked = false;

    private List<T> children = new ArrayList<T>();
  
}
