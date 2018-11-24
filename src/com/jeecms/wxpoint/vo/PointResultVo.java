/**
 * 
 */
package com.jeecms.wxpoint.vo;

/**
 * @author wanglijun
 *
 */
public class PointResultVo {
	
	private String code;
	
	private String message;
	
	private Long pointNum;
	
	private String type;
	
	
	public PointResultVo() {
		super();
	}
	
	
	

	/**
	 * @param code
	 * @param message
	 */
	public PointResultVo(String code, String message,String type) {
		this.code = code;
		this.message = message;
		this.type=type;
	}

	
	


	/**
	 * @param code
	 * @param message
	 * @param pointNum
	 * @param type
	 */
	public PointResultVo(String code, String message, Long pointNum,
			String type) {
		this.code = code;
		this.message = message;
		this.pointNum = pointNum;
		this.type = type;
	}




	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the pointNum
	 */
	public Long getPointNum() {
		return pointNum;
	}

	/**
	 * @param pointNum the pointNum to set
	 */
	public void setPointNum(Long pointNum) {
		this.pointNum = pointNum;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
