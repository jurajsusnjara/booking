package hr.fer.opp.controllers.pics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import hr.fer.opp.model.Korisnik;

//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PiePlot3D;
//import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.data.general.PieDataset;
//import org.jfree.util.Rotation;

@WebServlet("/gradovi")
public class PieChartGradovi extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		 Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");
		 if (korisnik == null || korisnik.getUloga() != 3) {
			 resp.sendRedirect("/opp-webapp/");
			 return;
		 }
		
		@SuppressWarnings("unchecked")
		Map<String, Integer> gradovi = (Map<String, Integer>) req.getSession().getAttribute("gradovi");
		PieDataset dataset = createDataset(gradovi);
		JFreeChart chart = createChart(dataset, "Gradovi");
		
		BufferedImage image = chart.createBufferedImage(500, 500);
		
		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(image, "PNG", sos);
	}

	private JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);
		
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
	}

	private PieDataset createDataset(Map<String, Integer> drzave) {
		
		Set<Entry<String, Integer>> set = drzave.entrySet();
		DefaultPieDataset result = new DefaultPieDataset();
		
		for(Entry<String, Integer> drzava : set) {
			
			result.setValue(drzava.getKey(), drzava.getValue());
		}
		
		return result;
	}
}
