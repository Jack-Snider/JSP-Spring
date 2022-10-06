package kr.or.ddit.explorer;

public interface FancyTreeNode extends Comparable<FancyTreeNode>{
	public String getTitle();
	public boolean isFolder();
	public String getKey();
	public boolean isLazy();
	
	@Override
	public default int compareTo(FancyTreeNode other) {
		boolean mine = isFolder();
		boolean otherFlag = other.isFolder();
		if(mine ^ otherFlag) {
			return mine? -1 : 1;
		}else {
			return getTitle().toUpperCase().compareTo(other.getTitle().toUpperCase());
		}
	}
}
