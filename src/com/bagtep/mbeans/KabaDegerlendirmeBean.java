package com.bagtep.mbeans;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.bagtep.business.DersService;
import com.bagtep.business.KabaDegerlendirmeService;
import com.bagtep.business.OgrenciService;
import com.bagtep.business.TestDataService;
import com.bagtep.domain.GenelAmac;
import com.bagtep.domain.KabaDegerlendirme;
import com.bagtep.domain.OzelAmac;

@ManagedBean
@ViewScoped
public class KabaDegerlendirmeBean {

	List<KabaDegerlendirme> kabadegerlendirme2 = new ArrayList<KabaDegerlendirme>();
	KabaDegerlendirme kabadegerlendirme;
	private int ogrenciId;
	private int dersId;
	private String dersAd;
	private String ogrenciAd;
	private String ogrenciSoyad;
	private boolean kabaDegerlendirmeCevap = false;
	private GenelAmac genelAmac;
	private Map<Integer, Boolean> ozelAmaclarMap = new LinkedHashMap<Integer, Boolean>();
	private Map<Integer, String> ozelAmacYorum = new LinkedHashMap<Integer, String>();
	private Map<Integer, OzelAmac> ozelAmacIdMap = new LinkedHashMap<Integer, OzelAmac>();
	private OzelAmac ozelAmac;
	private int genelAmacSayisi;
	private List<GenelAmac> genelAmaclar = new ArrayList<>();

	@EJB
	private TestDataService testDataService;
	@EJB
	private KabaDegerlendirmeService kabaDegerlendirmeService;
	@EJB
	private OgrenciService ogrenciService;
	@EJB
	private DersService dersService;

	@ManagedProperty("#{mySessionScopedBean}")
	private MySessionScopedBean mySessionScopedBean;

	@PostConstruct
	public void init() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		ogrenciId = Integer.parseInt(req.getParameter("oid"));
		this.dersAd = req.getParameter("did");

		this.ogrenciAd = ogrenciService.getOgrenciAd(ogrenciId);
		this.ogrenciSoyad = ogrenciService.getOgrenciSoyad(ogrenciId);
		this.dersId = dersService.getId(dersAd);

		this.genelAmacSayisi = dersService.genelAmacSayisiniBul(dersAd);
		System.out.println("İlgili Dersin Genel Amaç Sayısı : " + genelAmacSayisi);
		
		for (int i = 1; i <= genelAmacSayisi; i++) {
			this.genelAmac = testDataService.getGenelAmac(dersId, i);
			this.genelAmaclar.add(genelAmac);
			
			for (OzelAmac o : genelAmac.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			
			for (OzelAmac o : genelAmac.getOzelAmaclar()) {
				this.ozelAmacYorum.put(o.getId(), "");
			}
			
			for (OzelAmac o : genelAmac.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
		}
	}

	public void degerlendirmeKaydet() {
		// testMethod();

		String degerlendirici = mySessionScopedBean.getFirstname() + " " + mySessionScopedBean.getLastname();

		kabaDegerlendirmeService.degerlendirmeKaydet(ogrenciId, dersAd, degerlendirici, ozelAmaclarMap, ozelAmacYorum,
				ozelAmacIdMap);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Kaba Değerlendirme Başarıyla Kaydedildi !!!"));
	}

	public void degerlendirmeKaydetBos() {
	}

	// Konsoldan kabadeğerlendirme cevaplarını kontrol etmek için..
	public void testMethod() {
		for (Integer key : this.ozelAmaclarMap.keySet()) {
		}
	}

	public boolean dahaOnceDegerlendirilmismi() {

		boolean result = kabaDegerlendirmeService.dahaOnceDegerlendirilmismi(ogrenciId, dersAd);
		return result;
	}

	public boolean dahaOnceKabaDegerlendirilmismi() {

		boolean result = kabaDegerlendirmeService.dahaOnceKabaDegerlendirilmismi(ogrenciId, dersAd);
		return result;
	}

	public GenelAmac getGenelAmac() {
		return genelAmac;
	}

	public void setGenelAmac(GenelAmac genelAmac) {
		this.genelAmac = genelAmac;
	}

	public Map<Integer, Boolean> getOzelAmaclarMap() {
		return ozelAmaclarMap;
	}

	public void setOzelAmaclarMap(Map<Integer, Boolean> ozelAmaclarMap) {
		this.ozelAmaclarMap = ozelAmaclarMap;
	}

	public List<KabaDegerlendirme> getKabadegerlendirme2() {
		return kabadegerlendirme2;
	}

	public void setKabadegerlendirme2(List<KabaDegerlendirme> kabadegerlendirme2) {
		this.kabadegerlendirme2 = kabadegerlendirme2;
	}

	public KabaDegerlendirme getKabadegerlendirme() {
		return kabadegerlendirme;
	}

	public void setKabadegerlendirme(KabaDegerlendirme kabadegerlendirme) {
		this.kabadegerlendirme = kabadegerlendirme;
	}

	public int getOgrenciId() {
		return ogrenciId;
	}

	public void setOgrenciId(int ogrenciId) {
		this.ogrenciId = ogrenciId;
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

	public boolean isKabaDegerlendirmeCevap() {
		return kabaDegerlendirmeCevap;
	}

	public void setKabaDegerlendirmeCevap(boolean kabaDegerlendirmeCevap) {
		this.kabaDegerlendirmeCevap = kabaDegerlendirmeCevap;
	}

	public MySessionScopedBean getMySessionScopedBean() {
		return mySessionScopedBean;
	}

	public void setMySessionScopedBean(MySessionScopedBean mySessionScopedBean) {
		this.mySessionScopedBean = mySessionScopedBean;
	}

	public Map<Integer, String> getOzelAmacYorum() {
		return ozelAmacYorum;
	}

	public void setOzelAmacYorum(Map<Integer, String> ozelAmacYorum) {
		this.ozelAmacYorum = ozelAmacYorum;
	}

	public int getDersId() {
		return dersId;
	}

	public void setDersId(int dersId) {
		this.dersId = dersId;
	}

	public TestDataService getTestDataService() {
		return testDataService;
	}

	public void setTestDataService(TestDataService testDataService) {
		this.testDataService = testDataService;
	}

	public KabaDegerlendirmeService getKabaDegerlendirmeService() {
		return kabaDegerlendirmeService;
	}

	public void setKabaDegerlendirmeService(KabaDegerlendirmeService kabaDegerlendirmeService) {
		this.kabaDegerlendirmeService = kabaDegerlendirmeService;
	}

	public OgrenciService getOgrenciService() {
		return ogrenciService;
	}

	public void setOgrenciService(OgrenciService ogrenciService) {
		this.ogrenciService = ogrenciService;
	}

	public DersService getDersService() {
		return dersService;
	}

	public void setDersService(DersService dersService) {
		this.dersService = dersService;
	}

	public OzelAmac getOzelAmac() {
		return ozelAmac;
	}

	public void setOzelAmac(OzelAmac ozelAmac) {
		this.ozelAmac = ozelAmac;
	}

	public Map<Integer, OzelAmac> getOzelAmacIdMap() {
		return ozelAmacIdMap;
	}

	public void setOzelAmacIdMap(Map<Integer, OzelAmac> ozelAmacIdMap) {
		this.ozelAmacIdMap = ozelAmacIdMap;
	}

	public int getGenelAmacSayisi() {
		return genelAmacSayisi;
	}

	public void setGenelAmacSayisi(int genelAmacSayisi) {
		this.genelAmacSayisi = genelAmacSayisi;
	}

	public List<GenelAmac> getGenelAmaclar() {
		return genelAmaclar;
	}

	public void setGenelAmaclar(List<GenelAmac> genelAmaclar) {
		this.genelAmaclar = genelAmaclar;
	}

	public void pressPrintScreen() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_PRINTSCREEN);
		r.keyRelease(KeyEvent.VK_PRINTSCREEN);
	}

}
