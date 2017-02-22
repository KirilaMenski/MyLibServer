package by.ansgar.navigation.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

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

	public static String convertAndSaveImage(String bytes, String fileName, String folder) {
		String imagePath = "/home/kirila/MyProgramms/BookNavigation/image/" + folder + "/" + fileName + ".png";
		byte[] imageBytes = DatatypeConverter.parseBase64Binary(bytes);
		try {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
			ImageIO.write(img, "png", new File(imagePath));
		} catch (IOException e) {
			imagePath = "/home/kirila/MyProgramms/BookNavigation/image/defaultImage.jpg";
			e.printStackTrace();
		}
		return imagePath;
	}

}
