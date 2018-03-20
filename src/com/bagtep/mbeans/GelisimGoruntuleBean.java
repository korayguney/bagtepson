package com.bagtep.mbeans;

import java.awt.AWTException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import com.bagtep.business.DersService;
import com.bagtep.business.DonemDegerlendirmeService;
import com.bagtep.business.KabaDegerlendirmeService;
import com.bagtep.business.OgrenciService;
import com.bagtep.business.YilSonuDegerlendirmeService;
import com.bagtep.domain.Ders;
import com.bagtep.domain.DonemDegerlendirme;
import com.bagtep.domain.KabaDegerlendirme;
import com.bagtep.domain.YilSonuDegerlendirme;
import com.bagtep.utils.PartialScreenCaptureExample;

@ManagedBean
public class GelisimGoruntuleBean implements Serializable {

	private LineChartModel lineModel1;
	private int ogrenciId;
	private int dersId;
	private String dersAd;
	private String ogrenciAd;
	private String ogrenciSoyad;
	private Ders ders;
	private List<Ders> tumdersler;

	@EJB
	private KabaDegerlendirmeService kabaDegerlendirmeService;
	@EJB
	private DonemDegerlendirmeService donemDegerlendirmeService;
	@EJB
	private YilSonuDegerlendirmeService yilSonuDegerlendirmeService;
	@EJB
	private OgrenciService ogrenciService;
	@EJB
	private DersService dersService;
	private double yonergeyeGoreYilSonuPuani;

	@PostConstruct
	public void init() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		ogrenciId = Integer.parseInt(req.getParameter("oid"));
		this.dersAd = req.getParameter("did");

		this.ogrenciAd = ogrenciService.getOgrenciAd(ogrenciId);
		this.ogrenciSoyad = ogrenciService.getOgrenciSoyad(ogrenciId);
		this.dersId = dersService.getId(dersAd);
		this.tumdersler = dersService.getAllDers();

		createLineModels();
	}

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	private void createLineModels() {
		lineModel1 = initCategoryModel();
		lineModel1.setTitle("Gelişim Grafiği");
		lineModel1.setLegendPosition("e");
		lineModel1.setShowPointLabels(true);
		lineModel1.setAnimate(true);
		lineModel1.getAxes().put(AxisType.X, new CategoryAxis("degerlendirme"));
		Axis yAxis = lineModel1.getAxis(AxisType.Y);
		yAxis.setLabel("Değerlendirme Notu");
		yAxis.setMin(0);
		yAxis.setMax(100);

		Axis xAxis = lineModel1.getAxis(AxisType.X);
		xAxis.setLabel("Değerlendirme Takvimi");

	}

	private LineChartModel initCategoryModel() {
		LineChartModel model = new LineChartModel();

		ChartSeries series1 = new ChartSeries();
		series1.setLabel(dersAd);

		List<KabaDegerlendirme> kd = kabaDegerlendirmeService.kabaDegerlendirmeleriGetir(ogrenciId, dersId);
		List<DonemDegerlendirme> dd = null;
		try {
			dd = donemDegerlendirmeService.donemDegerlendirmeleriGetir(ogrenciId, dersId);
		} catch (Exception e) {
		}

		List<YilSonuDegerlendirme> ysd = null;
		try {
			ysd = yilSonuDegerlendirmeService.yilSonuDegerlendirmeleriGetir(ogrenciId, dersId);
		} catch (Exception e) {
		}

		if (kd != null) {
			for (KabaDegerlendirme kabaDegerlendirme : kd) {
				int kabaDegerlendirmePuan = kabaDegerlendirmeService.degerlendirmePuanHesapla(ogrenciId, dersAd,
						kabaDegerlendirme.getSinifAd());

				series1.set(kabaDegerlendirme.toString(), kabaDegerlendirmePuan);
				model.addSeries(series1);

				for (DonemDegerlendirme donemDegerlendirme : dd) {
					if (dd != null) {
						List<Double> donemDegerlendirmePuan = donemDegerlendirmeService.degerlendirmePuanHesapla(ogrenciId,
								dersAd, donemDegerlendirme.getSinifAd());

						series1.set(donemDegerlendirme.toString(), donemDegerlendirmePuan.get(0));
						model.addSeries(series1);

					} else {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_FATAL, "", ogrenciAd + " " + ogrenciSoyad
										+ " için " + dersAd + " dersi Durum Değerlendirmesi yapılmamıştır."));
					}

					for (YilSonuDegerlendirme yilSonuDegerlendirme : ysd) {
						if (ysd != null) {
							List<Double> yilSonuDegerlendirmePuan = yilSonuDegerlendirmeService
									.degerlendirmePuanHesapla(ogrenciId, dersAd, yilSonuDegerlendirme.getSinifAd());

							series1.set(yilSonuDegerlendirme.toString(), yilSonuDegerlendirmePuan.get(0));
							System.out.println("YILSONU PUANNNNNNNNN : "+yilSonuDegerlendirmePuan.get(1));
							yonergeyeGoreYilSonuPuani = yilSonuDegerlendirmePuan.get(1);
							model.addSeries(series1);
						} else {
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage(FacesMessage.SEVERITY_FATAL, "", ogrenciAd + " " + ogrenciSoyad
											+ " için " + dersAd + " dersi Yıl Sonu Değerlendirmesi yapılmamıştır."));
						}
					}
				}
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "",
					ogrenciAd + " " + ogrenciSoyad + " ın " + dersAd + " için Kaba Değerlendirmesi yapılmamıştır."));
		}

		return model;
	}

	public int yonergeyeGoreYilSonuPuaniniGetir() {
		return (int) yonergeyeGoreYilSonuPuani;
	}
	
	public int getOgrenciId() {
		return ogrenciId;
	}

	public void setOgrenciId(int ogrenciId) {
		this.ogrenciId = ogrenciId;
	}

	public int getDersId() {
		return dersId;
	}

	public void setDersId(int dersId) {
		this.dersId = dersId;
	}

	public String getDersAd() {
		return dersAd;
	}

	public void setDersAd(String dersAd) {
		this.dersAd = dersAd;
	}

	public String getOgrenciAd() {
		return ogrenciAd;
	}

	public void setOgrenciAd(String ogrenciAd) {
		this.ogrenciAd = ogrenciAd;
	}

	public String getOgrenciSoyad() {
		return ogrenciSoyad;
	}

	public void setOgrenciSoyad(String ogrenciSoyad) {
		this.ogrenciSoyad = ogrenciSoyad;
	}

	public void setLineModel1(LineChartModel lineModel1) {
		this.lineModel1 = lineModel1;
	}

	public Ders getDers() {
		return ders;
	}

	public void setDers(Ders ders) {
		this.ders = ders;
	}

	public List<Ders> getTumdersler() {
		return tumdersler;
	}

	public void setTumdersler(List<Ders> tumdersler) {
		this.tumdersler = tumdersler;
	}

	public double getYonergeyeGoreYilSonuPuani() {
		return yonergeyeGoreYilSonuPuani;
	}

	public void setYonergeyeGoreYilSonuPuani(double yonergeyeGoreYilSonuPuani) {
		this.yonergeyeGoreYilSonuPuani = yonergeyeGoreYilSonuPuani;
	}

	public void ekranResmiCek() {
		PartialScreenCaptureExample psc = new PartialScreenCaptureExample();
	}

	public void pressPrintScreen() throws AWTException, IOException {
//		Robot r = new Robot();
//		r.keyPress(KeyEvent.VK_PRINTSCREEN);
//		r.keyRelease(KeyEvent.VK_PRINTSCREEN);
		
//		Rectangle rec = new Rectangle(
//			      Toolkit.getDefaultToolkit().getScreenSize());
//			    Robot robot = new Robot();
//			    BufferedImage img = robot.createScreenCapture(rec);
//			     
//			    ImageIO.write(img, "jpg",new File("C:/Users/kgb20/Desktop"));

	}

}