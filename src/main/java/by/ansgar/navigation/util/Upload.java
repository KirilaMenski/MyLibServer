package by.ansgar.navigation.util;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class Upload {

	private static final Logger LOG = Logger.getLogger(Upload.class);

	public static String path;

	public static synchronized void doUpload(MultipartFile multipartFile, String folder) {

		String orgName = multipartFile.getOriginalFilename();
		String filePlaceToUpload =
		// "C:/";
		"/home/kirila/MyProgramms/BookNavigation/image/" + folder + "/";
		String filePath = filePlaceToUpload + orgName;
		File dest = new File(filePath);

		try {
				multipartFile.transferTo(dest);
				path = dest.toString();
			
		} catch (IllegalStateException e) {
			LOG.warn(e);
		} catch (IOException e) {
			path = "/home/kirila/MyProgramms/BookNavigation/image/defaultImage.jpg";
			LOG.warn(e);
		}
	}
}
