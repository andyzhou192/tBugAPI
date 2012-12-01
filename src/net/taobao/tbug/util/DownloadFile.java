package net.taobao.tbug.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DownloadFile {

	public static void download(String fileName, OutputStream outputStream) throws IOException{
		String dataPathname = UploadUtil.getDataPath()+File.separator+fileName;
		
		InputStream dataInputStream = new FileInputStream(dataPathname);
		byte[] buffer = new byte[1024];
		int i = -1;
		while ((i = dataInputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, i);
		}
		outputStream.flush();
		outputStream.close();
		outputStream.close();
		outputStream = null;
	}
}
