package net.taobao.tbug.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 用于文件上传的实用类
 * @author: zhouyelin
 * @date: 2013-2-1 上午 10:20:47
 */
public final class UploadUtil{
	private static URL base = UploadUtil.class.getResource("");
	/**
	 * 上传路径
	 */
	private static String uploadPath;

	/**
	 * 取得sdk上传路径
	 * @return
	 */
	public static String getSDKPath() {
		String sdkPath = null;
		try {
			sdkPath = (new File(base.getFile(),"../../../../../../sdk/").getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sdkPath;
	}
	
	/**
	 * 取得api上传路径
	 * @return
	 */
	public static String getAPIPath() {
		String apiPath = null;
		try {
			apiPath = (new File(base.getFile(),"../../../../../../api/").getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apiPath;
	}

	/**
	 * 取得data上传路径
	 * @return
	 */
	public static String getDataPath() {
		String apiPath = null;
		try {
			apiPath = (new File(base.getFile(),"../../../../../../data/").getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apiPath;
	}
	
	/**
	 * 设置上传路径
	 * @param uploadPath
	 */
	public static void setUploadPath(String uploadPath) {
		UploadUtil.uploadPath = uploadPath+"/tBugApi/upload/";
	}	
}