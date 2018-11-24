package com.jeecms.point.vo.competitiveproduct;

/**
 * 精品附件VO
 * 
 * @author ziv.hung
 *
 */
public class ImCompetitiveVO {
	private String code;
	private String name;

	public ImCompetitiveVO(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public ImCompetitiveVO() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImCompetitiveVO [code=" + code + ", name=" + name + "]";
	}

}
