<%@page import="java.io.IOException"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.OutputStream"%>
<%@page contentType="image/png"%>

<%     
try {
	BufferedImage image = (BufferedImage) request
			.getAttribute("image");

	OutputStream os = response.getOutputStream();
	ImageIO.write(image, "png", os);
	os.flush();
	os.close();
} catch (IOException e1) {}
%>