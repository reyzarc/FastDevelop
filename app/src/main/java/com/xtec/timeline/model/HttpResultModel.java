package com.xtec.timeline.model;

/**
 * Created by 冯浩 on 16/7/12.
 */
public class HttpResultModel<T> {

	private int ret;

	private String msg;
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	private String session_key;

	private String auto_pwd;

	private T data;

	private String url;

	private String version;
	private String feature;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getAuto_pwd() {
		return auto_pwd;
	}

	public void setAuto_pwd(String auto_pwd) {
		this.auto_pwd = auto_pwd;
	}

	public void setData(T data) {
		this.data = data;
	}


}
