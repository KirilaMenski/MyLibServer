package by.ansgar.navigation.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetBooksImgServlet
 */
@WebServlet("/ShowBooksImg")
public class GetBooksImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBooksImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String path = request.getParameter("path");
				try{
				File file = new File(path);
				byte[] img = getBytesFromFile(file);
				response.setContentType("image/jpg");
				OutputStream os = response.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(os);
				bos.write(img, 0, img.length);
				bos.close();
				}catch(FileNotFoundException e){
					
				}
			}

			public static byte[] getBytesFromFile(File file) throws IOException {
				InputStream is = new FileInputStream(file);

				long length = file.length();

				if (length > Integer.MAX_VALUE) {
					System.out.println("Too large");
				}

				byte[] bytes = new byte[(int) length];
				int offset = 0;
				int numRead = 0;
				while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) > 0) {
					offset += numRead;
				}

				if (offset < bytes.length) {
					throw new IOException("Could not completely read file" + file.getName());
				}
				is.close();
				return bytes;
			}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
