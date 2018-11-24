package com.jeecms.cms.entity.main;

public class ClubDealer {

	
	 private long id;
		
	 private String code;
	 
	 private long cityId;
	 
		private String name;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id; 
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public long getCityId() {
			return cityId;
		}

		public void setCityId(long cityId) {
			this.cityId = cityId;
		}
		
}
