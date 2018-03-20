package com.bagtep.mbeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.bagtep.domain.KabaDegerlendirmeKazanimCevap;
import com.bagtep.domain.Kazanim;
import com.bagtep.domain.OzelAmac;

@ManagedBean
@ViewScoped
public class KabaDegerlendirmeGoruntuleBean {

	List<KabaDegerlendirme> kabadegerlendirme2 = new ArrayList<KabaDegerlendirme>();
	List<KabaDegerlendirmeKazanimCevap> kabadegerlendirmekazanimcevap = new ArrayList<>();
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
	private Date degerlendirmeTarihi;
	private int genelAmacSayisi;
	private List<GenelAmac> genelAmaclar = new ArrayList<>();
	private static int ozelAmacSayisi = 0;
	private static int ozelAmacSayisi2 = 0;

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

		kabadegerlendirme = kabaDegerlendirmeService.kabaDegerlendirmeGetir2(ogrenciId, dersId);
		kabadegerlendirmekazanimcevap = kabadegerlendirme.getKabaDegerlendirmeKazanimCevap();

		degerlendirmeTarihi = kabadegerlendirme.getDegerlendirmeTarihi();

		this.genelAmacSayisi = dersService.genelAmacSayisiniBul(dersAd);
		
		for (int i = 1; i <= genelAmacSayisi; i++) {
			this.genelAmac = testDataService.getGenelAmac(dersId, i);
			
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(),
						kabadegerlendirmekazanimcevap.get(ozelAmacSayisi).isKabaDegerlendirmeCevap());
				ozelAmacSayisi++;
			}

			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmacYorum.put(o.getId(), kabadegerlendirmekazanimcevap.get(ozelAmacSayisi2).getYorum());
				ozelAmacSayisi2++;
			}

			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
		}
		
		ozelAmacSayisi = 0;
		ozelAmacSayisi2 = 0;
	}

	public void degerlendirmeKaydet() {

		String degerlendirici = mySessionScopedBean.getFirstname() + " " + mySessionScopedBean.getLastname();

		kabaDegerlendirmeService.degerlendirmeKaydet(ogrenciId, dersAd, degerlendirici, ozelAmaclarMap, ozelAmacYorum,
				ozelAmacIdMap);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Kaba Değerlendirme Başarıyla Kaydedildi !!!"));
	}

	public String degerlendirmeGoruntuleKaydetAdmin() {

		String degerlendirici = mySessionScopedBean.getFirstname() + " " + mySessionScopedBean.getLastname();

		kabaDegerlendirmeService.degerlendirmeGoruntuleKaydet(ogrenciId, dersAd, degerlendirici, ozelAmaclarMap,
				ozelAmacYorum, ozelAmacIdMap);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Kaba Değerlendirme Başarıyla Düzenlendi !!!"));
		return "adminkabadegerlendirmegoruntuleogrencisec";
	}
	
	public String degerlendirmeGoruntuleKaydetOgretmen() {

		String degerlendirici = mySessionScopedBean.getFirstname() + " " + mySessionScopedBean.getLastname();

		kabaDegerlendirmeService.degerlendirmeGoruntuleKaydet(ogrenciId, dersAd, degerlendirici, ozelAmaclarMap,
				ozelAmacYorum, ozelAmacIdMap);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Kaba Değerlendirme Başarıyla Düzenlendi !!!"));
		return "ogretmenkabadegerlendirmegoruntuleogrencisec";
	}


	public void degerlendirmeGoruntuleKaydetBos() {
	}
	
	public String degerlendirmeTarihiFormatla() {
		
		Date kdtarihi = getDegerlendirmeTarihi();
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		String date = DATE_FORMAT.format(kdtarihi);
		
		return date;
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

	public static int getOzelAmacSayisi() {
		return ozelAmacSayisi;
	}

	public static void setOzelAmacSayisi(int ozelAmacSayisi) {
		KabaDegerlendirmeGoruntuleBean.ozelAmacSayisi = ozelAmacSayisi;
	}

	public static int getOzelAmacSayisi2() {
		return ozelAmacSayisi2;
	}

	public static void setOzelAmacSayisi2(int ozelAmacSayisi2) {
		KabaDegerlendirmeGoruntuleBean.ozelAmacSayisi2 = ozelAmacSayisi2;
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

	public Date getDegerlendirmeTarihi() {
		return degerlendirmeTarihi;
	}

	public void setDegerlendirmeTarihi(Date degerlendirmeTarihi) {
		this.degerlendirmeTarihi = degerlendirmeTarihi;
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
	
	public List<KabaDegerlendirmeKazanimCevap> getKabadegerlendirmekazanimcevap() {
		return kabadegerlendirmekazanimcevap;
	}

	public void setKabadegerlendirmekazanimcevap(List<KabaDegerlendirmeKazanimCevap> kabadegerlendirmekazanimcevap) {
		this.kabadegerlendirmekazanimcevap = kabadegerlendirmekazanimcevap;
	}

	public void setGenelAmaclar(List<GenelAmac> genelAmaclar) {
		this.genelAmaclar = genelAmaclar;
	}

}
