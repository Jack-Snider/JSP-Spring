package kr.or.ddit.explorer;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FancyTreeNodeAdapter implements FancyTreeNode {
	
	@JsonIgnore
	private transient File adaptee;
	
	public FancyTreeNodeAdapter(File adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public String getTitle() {
		return adaptee.getName();
	}

	@Override
	public boolean isFolder() {
		return adaptee.isDirectory();
	}

	@Override
	public String getKey() {
		return adaptee.getName();
	}
	
	@Override
	public boolean isLazy() {
		return isFolder();
	}
}











