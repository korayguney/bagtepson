package com.bagtep.mbeans;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.bagtep.business.BireyselEgitimPlaniService;
import com.bagtep.business.DersService;
import com.bagtep.business.KabaDegerlendirmeService;
import com.bagtep.business.OgrenciService;
import com.bagtep.business.TestDataService;
import com.bagtep.domain.GenelAmac;
import com.bagtep.domain.Kazanim;
import com.bagtep.domain.OzelAmac;
import com.bagtep.domain.Sinif;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

@ManagedBean
@ViewScoped
public class BireyselEgitimPlaniBean {

	private int ogrenciId;
	private int dersId;
	private String dersAd;
	private String ogrenciAd;
	private String ogrenciSoyad;
	private String ogrenciDogum;
	private int ogrencino;
	private Sinif sinif;

	private String pdffilePath;
	private boolean pdfStatus;
	
	private GenelAmac genelAmac;
	private GenelAmac genelAmac2;
	private GenelAmac genelAmac3;
	private GenelAmac genelAmac4;
	private GenelAmac genelAmac5;
	private GenelAmac genelAmac6;
	private GenelAmac genelAmac7;
	private GenelAmac genelAmac8;
	private GenelAmac genelAmac9;
	private GenelAmac genelAmac10;
	private GenelAmac genelAmac11;
	private GenelAmac genelAmac12;
	private GenelAmac genelAmac13;
	private GenelAmac genelAmac14;
	private GenelAmac genelAmac15;
	private GenelAmac genelAmac16;
	private GenelAmac genelAmac17;
	private Map<Integer, Boolean> ozelAmaclarMap = new LinkedHashMap<Integer, Boolean>();
	private Map<Integer, OzelAmac> ozelAmacIdMap = new LinkedHashMap<Integer, OzelAmac>();

	private List<OzelAmac> ozelAmacHayirVerilen = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen1 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen2 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen3 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen4 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen5 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen6 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen7 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen8 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen9 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen10 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen11 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen12 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen13 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen14 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen15 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen16 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacHayirVerilen17 = new ArrayList<OzelAmac>();

	private List<OzelAmac> ozelAmacEvetVerilen = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen1 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen2 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen3 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen4 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen5 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen6 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen7 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen8 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen9 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen10 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen11 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen12 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen13 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen14 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen15 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen16 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacEvetVerilen17 = new ArrayList<OzelAmac>();
	
	private List<OzelAmac> ozelAmacBEPteSecilen = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen1 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen2 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen3 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen4 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen5 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen6 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen7 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen8 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen9 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen10 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen11 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen12 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen13 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen14 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen15 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen16 = new ArrayList<OzelAmac>();
	private List<OzelAmac> ozelAmacBEPteSecilen17 = new ArrayList<OzelAmac>();
	
	
	@EJB
	private TestDataService testDataService;
	@EJB
	private KabaDegerlendirmeService kabaDegerlendirmeService;
	@EJB
	private OgrenciService ogrenciService;
	@EJB
	private DersService dersService;
	@EJB
	private BireyselEgitimPlaniService bireyselEgitimPlaniService;

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
		this.ogrencino = ogrenciService.getOgrenciNo(ogrenciId);
		this.ogrenciDogum = ogrenciService.getOgrenciDogum(ogrenciId);
		this.sinif = ogrenciService.getOgrenciSinif(ogrenciId);
		this.dersId = dersService.getId(dersAd);
		//FIXME burada BEP'ten hayırlar alınabilir.
		ozelAmacHayirVerilen = bireyselEgitimPlaniService.kabaDegerlendirmedeHayirVerilenCevaplariGetir(dersId,ogrenciId);
		ozelAmacBEPteSecilen = bireyselEgitimPlaniService.bireyselEgitimPlaninaDahilEdilenleriGetir(dersId,ogrenciId);
//		if(ozelAmacBEPteSecilen.isEmpty()){
//			System.out.println("Özel Amaç BEP te verilenler ALINAMADI !");
//		}else {
//			for (OzelAmac ozelAmac : ozelAmacBEPteSecilen) {
//				System.out.println("Özel Amaç BEP te verilenler ALINDI");
//				System.out.println(ozelAmac.getIcerik());
//			}
//		}
		

		if (dersAd.equals("Matematik")) {
			this.genelAmac = testDataService.getGenelAmac(dersId, 1);
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}

			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac2 = testDataService.getGenelAmac(dersId, 2);
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac3 = testDataService.getGenelAmac(dersId, 3);
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac4 = testDataService.getGenelAmac(dersId, 4);
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac5 = testDataService.getGenelAmac(dersId, 5);
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac6 = testDataService.getGenelAmac(dersId, 6);
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
		} else if (dersAd.equals("Dil ve Konuşma Gelişimi")) {
			this.genelAmac = testDataService.getGenelAmac(dersId, 1);
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac2 = testDataService.getGenelAmac(dersId, 2);
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac3 = testDataService.getGenelAmac(dersId, 3);
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac4 = testDataService.getGenelAmac(dersId, 4);
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac5 = testDataService.getGenelAmac(dersId, 5);
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac6 = testDataService.getGenelAmac(dersId, 6);
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac7 = testDataService.getGenelAmac(dersId, 7);
			for (OzelAmac o : this.genelAmac7.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac7.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
		} else if (dersAd.equals("Din Kültürü ve Ahlak Bilgisi")) {
			this.genelAmac = testDataService.getGenelAmac(dersId, 1);
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac2 = testDataService.getGenelAmac(dersId, 2);
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac3 = testDataService.getGenelAmac(dersId, 3);
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac4 = testDataService.getGenelAmac(dersId, 4);
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
		} else if (dersAd.equals("Hayat Bilgisi")) {
			this.genelAmac = testDataService.getGenelAmac(dersId, 1);
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac2 = testDataService.getGenelAmac(dersId, 2);
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac3 = testDataService.getGenelAmac(dersId, 3);
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac4 = testDataService.getGenelAmac(dersId, 4);
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac5 = testDataService.getGenelAmac(dersId, 5);
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac6 = testDataService.getGenelAmac(dersId, 6);
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac7 = testDataService.getGenelAmac(dersId, 7);
			for (OzelAmac o : this.genelAmac7.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac7.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac8 = testDataService.getGenelAmac(dersId, 8);
			for (OzelAmac o : this.genelAmac8.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac8.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac9 = testDataService.getGenelAmac(dersId, 9);
			for (OzelAmac o : this.genelAmac9.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac9.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac10 = testDataService.getGenelAmac(dersId, 10);
			for (OzelAmac o : this.genelAmac10.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac10.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac11 = testDataService.getGenelAmac(dersId, 11);
			for (OzelAmac o : this.genelAmac11.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac11.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac12 = testDataService.getGenelAmac(dersId, 12);
			for (OzelAmac o : this.genelAmac12.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac12.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac13 = testDataService.getGenelAmac(dersId, 13);
			for (OzelAmac o : this.genelAmac13.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac13.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac14 = testDataService.getGenelAmac(dersId, 14);
			for (OzelAmac o : this.genelAmac14.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac14.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac15 = testDataService.getGenelAmac(dersId, 15);
			for (OzelAmac o : this.genelAmac15.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac15.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac16 = testDataService.getGenelAmac(dersId, 16);
			for (OzelAmac o : this.genelAmac16.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac16.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac17 = testDataService.getGenelAmac(dersId, 17);
			for (OzelAmac o : this.genelAmac17.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac17.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
		} else if (dersAd.equals("Müzik")) {
			this.genelAmac = testDataService.getGenelAmac(dersId, 1);
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac2 = testDataService.getGenelAmac(dersId, 2);
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac3 = testDataService.getGenelAmac(dersId, 3);
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac4 = testDataService.getGenelAmac(dersId, 4);
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac5 = testDataService.getGenelAmac(dersId, 5);
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac6 = testDataService.getGenelAmac(dersId, 6);
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac7 = testDataService.getGenelAmac(dersId, 7);
			for (OzelAmac o : this.genelAmac7.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac7.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac8 = testDataService.getGenelAmac(dersId, 8);
			for (OzelAmac o : this.genelAmac8.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac8.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac9 = testDataService.getGenelAmac(dersId, 9);
			for (OzelAmac o : this.genelAmac9.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac9.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac10 = testDataService.getGenelAmac(dersId, 10);
			for (OzelAmac o : this.genelAmac10.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac10.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac11 = testDataService.getGenelAmac(dersId, 11);
			for (OzelAmac o : this.genelAmac11.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac11.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
		} else if (dersAd.equals("Beslenme Bilgisi")) {
			this.genelAmac = testDataService.getGenelAmac(dersId, 1);
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac2 = testDataService.getGenelAmac(dersId, 2);
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac3 = testDataService.getGenelAmac(dersId, 3);
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac4 = testDataService.getGenelAmac(dersId, 4);
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac5 = testDataService.getGenelAmac(dersId, 5);
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
		} else if (dersAd.equals("Görsel Sanatlar")) {
			this.genelAmac = testDataService.getGenelAmac(dersId, 1);
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac2 = testDataService.getGenelAmac(dersId, 2);
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac3 = testDataService.getGenelAmac(dersId, 3);
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac4 = testDataService.getGenelAmac(dersId, 4);
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac5 = testDataService.getGenelAmac(dersId, 5);
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac6 = testDataService.getGenelAmac(dersId, 6);
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac7 = testDataService.getGenelAmac(dersId, 7);
			for (OzelAmac o : this.genelAmac7.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac7.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac8 = testDataService.getGenelAmac(dersId, 8);
			for (OzelAmac o : this.genelAmac8.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac8.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
		} else if (dersAd.equals("Okuma Yazma")) {
			this.genelAmac = testDataService.getGenelAmac(dersId, 1);
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac2 = testDataService.getGenelAmac(dersId, 2);
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac3 = testDataService.getGenelAmac(dersId, 3);
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac4 = testDataService.getGenelAmac(dersId, 4);
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac5 = testDataService.getGenelAmac(dersId, 5);
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac6 = testDataService.getGenelAmac(dersId, 6);
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
		} else if (dersAd.equals("Trafik ve İlk Yardım Eğitimi")) {
			this.genelAmac = testDataService.getGenelAmac(dersId, 1);
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac2 = testDataService.getGenelAmac(dersId, 2);
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac3 = testDataService.getGenelAmac(dersId, 3);
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac4 = testDataService.getGenelAmac(dersId, 4);
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac5 = testDataService.getGenelAmac(dersId, 5);
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac6 = testDataService.getGenelAmac(dersId, 6);
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
		} else if (dersAd.equals("Beden Eğitimi")) {
			this.genelAmac = testDataService.getGenelAmac(dersId, 1);
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac2 = testDataService.getGenelAmac(dersId, 2);
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac3 = testDataService.getGenelAmac(dersId, 3);
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac4 = testDataService.getGenelAmac(dersId, 4);
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac5 = testDataService.getGenelAmac(dersId, 5);
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac6 = testDataService.getGenelAmac(dersId, 6);
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac7 = testDataService.getGenelAmac(dersId, 7);
			for (OzelAmac o : this.genelAmac7.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac7.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac8 = testDataService.getGenelAmac(dersId, 8);
			for (OzelAmac o : this.genelAmac8.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac8.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
		} else if (dersAd.equals("Toplumsal Uyum Becerileri")) {
			this.genelAmac = testDataService.getGenelAmac(dersId, 1);
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac2 = testDataService.getGenelAmac(dersId, 2);
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac2.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac3 = testDataService.getGenelAmac(dersId, 3);
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac3.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac4 = testDataService.getGenelAmac(dersId, 4);
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac4.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac5 = testDataService.getGenelAmac(dersId, 5);
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac5.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
			this.genelAmac6 = testDataService.getGenelAmac(dersId, 6);
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmaclarMap.put(o.getId(), false);
			}
			for (OzelAmac o : this.genelAmac6.getOzelAmaclar()) {
				this.ozelAmacIdMap.put(o.getId(), o);
			}
		}

		for (OzelAmac ozelAmac : ozelAmacHayirVerilen) {
//		for (OzelAmac ozelAmac : ozelAmacBEPteSecilen) {
//			System.out.println("Özel Amaç : " + ozelAmac.getIcerik());
			if (ozelAmac.getGenelAmac().getId() == genelAmac.getId()) {
				ozelAmacHayirVerilen1.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac2.getId()) {
				ozelAmacHayirVerilen2.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac3.getId()) {
				ozelAmacHayirVerilen3.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac4.getId()) {
				ozelAmacHayirVerilen4.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac5.getId()) {
				ozelAmacHayirVerilen5.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac6.getId()) {
				ozelAmacHayirVerilen6.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac7.getId()) {
				ozelAmacHayirVerilen7.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac8.getId()) {
				ozelAmacHayirVerilen8.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac9.getId()) {
				ozelAmacHayirVerilen9.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac10.getId()) {
				ozelAmacHayirVerilen10.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac11.getId()) {
				ozelAmacHayirVerilen11.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac12.getId()) {
				ozelAmacHayirVerilen12.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac13.getId()) {
				ozelAmacHayirVerilen13.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac14.getId()) {
				ozelAmacHayirVerilen14.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac15.getId()) {
				ozelAmacHayirVerilen15.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac16.getId()) {
				ozelAmacHayirVerilen16.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac17.getId()) {
				ozelAmacHayirVerilen17.add(ozelAmac);
			}
			
			
			}
		for (OzelAmac ozelAmacBEP : ozelAmacBEPteSecilen) {
			if (ozelAmacBEP.getGenelAmac().getId() == genelAmac.getId()) {
				ozelAmacBEPteSecilen1.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac2.getId()) {
				ozelAmacBEPteSecilen2.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac3.getId()) {
				ozelAmacBEPteSecilen3.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac4.getId()) {
				ozelAmacBEPteSecilen4.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac5.getId()) {
				ozelAmacBEPteSecilen5.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac6.getId()) {
				ozelAmacBEPteSecilen6.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac7.getId()) {
				ozelAmacBEPteSecilen7.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac8.getId()) {
				ozelAmacBEPteSecilen8.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac9.getId()) {
				ozelAmacBEPteSecilen9.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac10.getId()) {
				ozelAmacBEPteSecilen10.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac11.getId()) {
				ozelAmacBEPteSecilen11.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac12.getId()) {
				ozelAmacBEPteSecilen12.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac13.getId()) {
				ozelAmacBEPteSecilen13.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac14.getId()) {
				ozelAmacBEPteSecilen14.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac15.getId()) {
				ozelAmacBEPteSecilen15.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac16.getId()) {
				ozelAmacBEPteSecilen16.add(ozelAmacBEP);
			} else if (ozelAmacBEP.getGenelAmac().getId() == genelAmac17.getId()) {
				ozelAmacBEPteSecilen17.add(ozelAmacBEP);
			}
		}
	}

	public void bireyselEgitimPlaniOlustur() {

		String degerlendirici = mySessionScopedBean.getFirstname() + " " + mySessionScopedBean.getLastname();

		bireyselEgitimPlaniService.bireyselEgitimPlaniAl(ogrenciId, dersAd, degerlendirici, ozelAmaclarMap,
				ozelAmacIdMap);
	}

	public void bireyselEgitimPlaniAl() {

	}

	public void bireyselEgitimPlaniAlBos() {

	}

	public boolean dahaOnceDegerlendirilmismi() {

		boolean result = bireyselEgitimPlaniService.dahaOnceDegerlendirilmismi(ogrenciId, dersAd);
		return result;
	}

	public boolean PdfCreated() {
		return pdfStatus;
	}
	
	public GenelAmac getGenelAmac() {
		return genelAmac;
	}

	public void setGenelAmac(GenelAmac genelAmac) {
		this.genelAmac = genelAmac;
	}

	public GenelAmac getGenelAmac2() {
		return genelAmac2;
	}

	public void setGenelAmac2(GenelAmac genelAmac2) {
		this.genelAmac2 = genelAmac2;
	}

	public GenelAmac getGenelAmac3() {
		return genelAmac3;
	}

	public void setGenelAmac3(GenelAmac genelAmac3) {
		this.genelAmac3 = genelAmac3;
	}

	public Map<Integer, Boolean> getOzelAmaclarMap() {
		return ozelAmaclarMap;
	}

	public void setOzelAmaclarMap(Map<Integer, Boolean> ozelAmaclarMap) {
		this.ozelAmaclarMap = ozelAmaclarMap;
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

	public int getOgrencino() {
		return ogrencino;
	}

	public void setOgrencino(int ogrencino) {
		this.ogrencino = ogrencino;
	}

	public Sinif getSinif() {
		return sinif;
	}

	public void setSinif(Sinif sinif) {
		this.sinif = sinif;
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

	public MySessionScopedBean getMySessionScopedBean() {
		return mySessionScopedBean;
	}

	public void setMySessionScopedBean(MySessionScopedBean mySessionScopedBean) {
		this.mySessionScopedBean = mySessionScopedBean;
	}

	public int getDersId() {
		return dersId;
	}

	public void setDersId(int dersId) {
		this.dersId = dersId;
	}

	public String getPdffilePath() {
		return pdffilePath;
	}

	public void setPdffilePath(String pdffilePath) {
		this.pdffilePath = pdffilePath;
	}

	public boolean isPdfStatus() {
		return pdfStatus;
	}

	public void setPdfStatus(boolean pdfStatus) {
		this.pdfStatus = pdfStatus;
	}

	public GenelAmac getGenelAmac4() {
		return genelAmac4;
	}

	public void setGenelAmac4(GenelAmac genelAmac4) {
		this.genelAmac4 = genelAmac4;
	}

	public GenelAmac getGenelAmac5() {
		return genelAmac5;
	}

	public void setGenelAmac5(GenelAmac genelAmac5) {
		this.genelAmac5 = genelAmac5;
	}

	public GenelAmac getGenelAmac6() {
		return genelAmac6;
	}

	public void setGenelAmac6(GenelAmac genelAmac6) {
		this.genelAmac6 = genelAmac6;
	}

	public GenelAmac getGenelAmac7() {
		return genelAmac7;
	}

	public void setGenelAmac7(GenelAmac genelAmac7) {
		this.genelAmac7 = genelAmac7;
	}

	public GenelAmac getGenelAmac8() {
		return genelAmac8;
	}

	public void setGenelAmac8(GenelAmac genelAmac8) {
		this.genelAmac8 = genelAmac8;
	}

	public GenelAmac getGenelAmac9() {
		return genelAmac9;
	}

	public void setGenelAmac9(GenelAmac genelAmac9) {
		this.genelAmac9 = genelAmac9;
	}

	public GenelAmac getGenelAmac10() {
		return genelAmac10;
	}

	public void setGenelAmac10(GenelAmac genelAmac10) {
		this.genelAmac10 = genelAmac10;
	}

	public GenelAmac getGenelAmac11() {
		return genelAmac11;
	}

	public void setGenelAmac11(GenelAmac genelAmac11) {
		this.genelAmac11 = genelAmac11;
	}

	public GenelAmac getGenelAmac12() {
		return genelAmac12;
	}

	public void setGenelAmac12(GenelAmac genelAmac12) {
		this.genelAmac12 = genelAmac12;
	}

	public GenelAmac getGenelAmac13() {
		return genelAmac13;
	}

	public void setGenelAmac13(GenelAmac genelAmac13) {
		this.genelAmac13 = genelAmac13;
	}

	public GenelAmac getGenelAmac14() {
		return genelAmac14;
	}

	public void setGenelAmac14(GenelAmac genelAmac14) {
		this.genelAmac14 = genelAmac14;
	}

	public GenelAmac getGenelAmac15() {
		return genelAmac15;
	}

	public void setGenelAmac15(GenelAmac genelAmac15) {
		this.genelAmac15 = genelAmac15;
	}

	public GenelAmac getGenelAmac16() {
		return genelAmac16;
	}

	public void setGenelAmac16(GenelAmac genelAmac16) {
		this.genelAmac16 = genelAmac16;
	}

	public GenelAmac getGenelAmac17() {
		return genelAmac17;
	}

	public void setGenelAmac17(GenelAmac genelAmac17) {
		this.genelAmac17 = genelAmac17;
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

	public List<OzelAmac> getOzelAmacHayirVerilen() {
		return ozelAmacHayirVerilen;
	}

	public void setOzelAmacHayirVerilen(List<OzelAmac> ozelAmacHayirVerilen) {
		this.ozelAmacHayirVerilen = ozelAmacHayirVerilen;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen1() {
		return ozelAmacHayirVerilen1;
	}

	public void setOzelAmacHayirVerilen1(List<OzelAmac> ozelAmacHayirVerilen1) {
		this.ozelAmacHayirVerilen1 = ozelAmacHayirVerilen1;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen2() {
		return ozelAmacHayirVerilen2;
	}

	public void setOzelAmacHayirVerilen2(List<OzelAmac> ozelAmacHayirVerilen2) {
		this.ozelAmacHayirVerilen2 = ozelAmacHayirVerilen2;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen3() {
		return ozelAmacHayirVerilen3;
	}

	public void setOzelAmacHayirVerilen3(List<OzelAmac> ozelAmacHayirVerilen3) {
		this.ozelAmacHayirVerilen3 = ozelAmacHayirVerilen3;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen4() {
		return ozelAmacHayirVerilen4;
	}

	public void setOzelAmacHayirVerilen4(List<OzelAmac> ozelAmacHayirVerilen4) {
		this.ozelAmacHayirVerilen4 = ozelAmacHayirVerilen4;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen5() {
		return ozelAmacHayirVerilen5;
	}

	public void setOzelAmacHayirVerilen5(List<OzelAmac> ozelAmacHayirVerilen5) {
		this.ozelAmacHayirVerilen5 = ozelAmacHayirVerilen5;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen6() {
		return ozelAmacHayirVerilen6;
	}

	public void setOzelAmacHayirVerilen6(List<OzelAmac> ozelAmacHayirVerilen6) {
		this.ozelAmacHayirVerilen6 = ozelAmacHayirVerilen6;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen7() {
		return ozelAmacHayirVerilen7;
	}

	public void setOzelAmacHayirVerilen7(List<OzelAmac> ozelAmacHayirVerilen7) {
		this.ozelAmacHayirVerilen7 = ozelAmacHayirVerilen7;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen8() {
		return ozelAmacHayirVerilen8;
	}

	public void setOzelAmacHayirVerilen8(List<OzelAmac> ozelAmacHayirVerilen8) {
		this.ozelAmacHayirVerilen8 = ozelAmacHayirVerilen8;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen9() {
		return ozelAmacHayirVerilen9;
	}

	public void setOzelAmacHayirVerilen9(List<OzelAmac> ozelAmacHayirVerilen9) {
		this.ozelAmacHayirVerilen9 = ozelAmacHayirVerilen9;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen10() {
		return ozelAmacHayirVerilen10;
	}

	public void setOzelAmacHayirVerilen10(List<OzelAmac> ozelAmacHayirVerilen10) {
		this.ozelAmacHayirVerilen10 = ozelAmacHayirVerilen10;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen11() {
		return ozelAmacHayirVerilen11;
	}

	public void setOzelAmacHayirVerilen11(List<OzelAmac> ozelAmacHayirVerilen11) {
		this.ozelAmacHayirVerilen11 = ozelAmacHayirVerilen11;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen12() {
		return ozelAmacHayirVerilen12;
	}

	public void setOzelAmacHayirVerilen12(List<OzelAmac> ozelAmacHayirVerilen12) {
		this.ozelAmacHayirVerilen12 = ozelAmacHayirVerilen12;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen13() {
		return ozelAmacHayirVerilen13;
	}

	public void setOzelAmacHayirVerilen13(List<OzelAmac> ozelAmacHayirVerilen13) {
		this.ozelAmacHayirVerilen13 = ozelAmacHayirVerilen13;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen14() {
		return ozelAmacHayirVerilen14;
	}

	public void setOzelAmacHayirVerilen14(List<OzelAmac> ozelAmacHayirVerilen14) {
		this.ozelAmacHayirVerilen14 = ozelAmacHayirVerilen14;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen15() {
		return ozelAmacHayirVerilen15;
	}

	public void setOzelAmacHayirVerilen15(List<OzelAmac> ozelAmacHayirVerilen15) {
		this.ozelAmacHayirVerilen15 = ozelAmacHayirVerilen15;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen16() {
		return ozelAmacHayirVerilen16;
	}

	public void setOzelAmacHayirVerilen16(List<OzelAmac> ozelAmacHayirVerilen16) {
		this.ozelAmacHayirVerilen16 = ozelAmacHayirVerilen16;
	}

	public List<OzelAmac> getOzelAmacHayirVerilen17() {
		return ozelAmacHayirVerilen17;
	}

	public void setOzelAmacHayirVerilen17(List<OzelAmac> ozelAmacHayirVerilen17) {
		this.ozelAmacHayirVerilen17 = ozelAmacHayirVerilen17;
	}

	public Map<Integer, OzelAmac> getOzelAmacIdMap() {
		return ozelAmacIdMap;
	}

	public void setOzelAmacIdMap(Map<Integer, OzelAmac> ozelAmacIdMap) {
		this.ozelAmacIdMap = ozelAmacIdMap;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen() {
		return ozelAmacEvetVerilen;
	}

	public void setOzelAmacEvetVerilen(List<OzelAmac> ozelAmacEvetVerilen) {
		this.ozelAmacEvetVerilen = ozelAmacEvetVerilen;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen1() {
		return ozelAmacEvetVerilen1;
	}

	public void setOzelAmacEvetVerilen1(List<OzelAmac> ozelAmacEvetVerilen1) {
		this.ozelAmacEvetVerilen1 = ozelAmacEvetVerilen1;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen2() {
		return ozelAmacEvetVerilen2;
	}

	public void setOzelAmacEvetVerilen2(List<OzelAmac> ozelAmacEvetVerilen2) {
		this.ozelAmacEvetVerilen2 = ozelAmacEvetVerilen2;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen3() {
		return ozelAmacEvetVerilen3;
	}

	public void setOzelAmacEvetVerilen3(List<OzelAmac> ozelAmacEvetVerilen3) {
		this.ozelAmacEvetVerilen3 = ozelAmacEvetVerilen3;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen4() {
		return ozelAmacEvetVerilen4;
	}

	public void setOzelAmacEvetVerilen4(List<OzelAmac> ozelAmacEvetVerilen4) {
		this.ozelAmacEvetVerilen4 = ozelAmacEvetVerilen4;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen5() {
		return ozelAmacEvetVerilen5;
	}

	public void setOzelAmacEvetVerilen5(List<OzelAmac> ozelAmacEvetVerilen5) {
		this.ozelAmacEvetVerilen5 = ozelAmacEvetVerilen5;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen6() {
		return ozelAmacEvetVerilen6;
	}

	public void setOzelAmacEvetVerilen6(List<OzelAmac> ozelAmacEvetVerilen6) {
		this.ozelAmacEvetVerilen6 = ozelAmacEvetVerilen6;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen7() {
		return ozelAmacEvetVerilen7;
	}

	public void setOzelAmacEvetVerilen7(List<OzelAmac> ozelAmacEvetVerilen7) {
		this.ozelAmacEvetVerilen7 = ozelAmacEvetVerilen7;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen8() {
		return ozelAmacEvetVerilen8;
	}

	public void setOzelAmacEvetVerilen8(List<OzelAmac> ozelAmacEvetVerilen8) {
		this.ozelAmacEvetVerilen8 = ozelAmacEvetVerilen8;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen9() {
		return ozelAmacEvetVerilen9;
	}

	public void setOzelAmacEvetVerilen9(List<OzelAmac> ozelAmacEvetVerilen9) {
		this.ozelAmacEvetVerilen9 = ozelAmacEvetVerilen9;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen10() {
		return ozelAmacEvetVerilen10;
	}

	public void setOzelAmacEvetVerilen10(List<OzelAmac> ozelAmacEvetVerilen10) {
		this.ozelAmacEvetVerilen10 = ozelAmacEvetVerilen10;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen11() {
		return ozelAmacEvetVerilen11;
	}

	public void setOzelAmacEvetVerilen11(List<OzelAmac> ozelAmacEvetVerilen11) {
		this.ozelAmacEvetVerilen11 = ozelAmacEvetVerilen11;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen12() {
		return ozelAmacEvetVerilen12;
	}

	public void setOzelAmacEvetVerilen12(List<OzelAmac> ozelAmacEvetVerilen12) {
		this.ozelAmacEvetVerilen12 = ozelAmacEvetVerilen12;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen13() {
		return ozelAmacEvetVerilen13;
	}

	public void setOzelAmacEvetVerilen13(List<OzelAmac> ozelAmacEvetVerilen13) {
		this.ozelAmacEvetVerilen13 = ozelAmacEvetVerilen13;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen14() {
		return ozelAmacEvetVerilen14;
	}

	public void setOzelAmacEvetVerilen14(List<OzelAmac> ozelAmacEvetVerilen14) {
		this.ozelAmacEvetVerilen14 = ozelAmacEvetVerilen14;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen15() {
		return ozelAmacEvetVerilen15;
	}

	public void setOzelAmacEvetVerilen15(List<OzelAmac> ozelAmacEvetVerilen15) {
		this.ozelAmacEvetVerilen15 = ozelAmacEvetVerilen15;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen16() {
		return ozelAmacEvetVerilen16;
	}

	public void setOzelAmacEvetVerilen16(List<OzelAmac> ozelAmacEvetVerilen16) {
		this.ozelAmacEvetVerilen16 = ozelAmacEvetVerilen16;
	}

	public List<OzelAmac> getOzelAmacEvetVerilen17() {
		return ozelAmacEvetVerilen17;
	}

	public void setOzelAmacEvetVerilen17(List<OzelAmac> ozelAmacEvetVerilen17) {
		this.ozelAmacEvetVerilen17 = ozelAmacEvetVerilen17;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen() {
		return ozelAmacBEPteSecilen;
	}

	public void setOzelAmacBEPteSecilen(List<OzelAmac> ozelAmacBEPteSecilen) {
		this.ozelAmacBEPteSecilen = ozelAmacBEPteSecilen;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen1() {
		return ozelAmacBEPteSecilen1;
	}

	public void setOzelAmacBEPteSecilen1(List<OzelAmac> ozelAmacBEPteSecilen1) {
		this.ozelAmacBEPteSecilen1 = ozelAmacBEPteSecilen1;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen2() {
		return ozelAmacBEPteSecilen2;
	}

	public void setOzelAmacBEPteSecilen2(List<OzelAmac> ozelAmacBEPteSecilen2) {
		this.ozelAmacBEPteSecilen2 = ozelAmacBEPteSecilen2;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen3() {
		return ozelAmacBEPteSecilen3;
	}

	public void setOzelAmacBEPteSecilen3(List<OzelAmac> ozelAmacBEPteSecilen3) {
		this.ozelAmacBEPteSecilen3 = ozelAmacBEPteSecilen3;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen4() {
		return ozelAmacBEPteSecilen4;
	}

	public void setOzelAmacBEPteSecilen4(List<OzelAmac> ozelAmacBEPteSecilen4) {
		this.ozelAmacBEPteSecilen4 = ozelAmacBEPteSecilen4;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen5() {
		return ozelAmacBEPteSecilen5;
	}

	public void setOzelAmacBEPteSecilen5(List<OzelAmac> ozelAmacBEPteSecilen5) {
		this.ozelAmacBEPteSecilen5 = ozelAmacBEPteSecilen5;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen6() {
		return ozelAmacBEPteSecilen6;
	}

	public void setOzelAmacBEPteSecilen6(List<OzelAmac> ozelAmacBEPteSecilen6) {
		this.ozelAmacBEPteSecilen6 = ozelAmacBEPteSecilen6;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen7() {
		return ozelAmacBEPteSecilen7;
	}

	public void setOzelAmacBEPteSecilen7(List<OzelAmac> ozelAmacBEPteSecilen7) {
		this.ozelAmacBEPteSecilen7 = ozelAmacBEPteSecilen7;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen8() {
		return ozelAmacBEPteSecilen8;
	}

	public void setOzelAmacBEPteSecilen8(List<OzelAmac> ozelAmacBEPteSecilen8) {
		this.ozelAmacBEPteSecilen8 = ozelAmacBEPteSecilen8;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen9() {
		return ozelAmacBEPteSecilen9;
	}

	public void setOzelAmacBEPteSecilen9(List<OzelAmac> ozelAmacBEPteSecilen9) {
		this.ozelAmacBEPteSecilen9 = ozelAmacBEPteSecilen9;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen10() {
		return ozelAmacBEPteSecilen10;
	}

	public void setOzelAmacBEPteSecilen10(List<OzelAmac> ozelAmacBEPteSecilen10) {
		this.ozelAmacBEPteSecilen10 = ozelAmacBEPteSecilen10;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen11() {
		return ozelAmacBEPteSecilen11;
	}

	public void setOzelAmacBEPteSecilen11(List<OzelAmac> ozelAmacBEPteSecilen11) {
		this.ozelAmacBEPteSecilen11 = ozelAmacBEPteSecilen11;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen12() {
		return ozelAmacBEPteSecilen12;
	}

	public void setOzelAmacBEPteSecilen12(List<OzelAmac> ozelAmacBEPteSecilen12) {
		this.ozelAmacBEPteSecilen12 = ozelAmacBEPteSecilen12;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen13() {
		return ozelAmacBEPteSecilen13;
	}

	public void setOzelAmacBEPteSecilen13(List<OzelAmac> ozelAmacBEPteSecilen13) {
		this.ozelAmacBEPteSecilen13 = ozelAmacBEPteSecilen13;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen14() {
		return ozelAmacBEPteSecilen14;
	}

	public void setOzelAmacBEPteSecilen14(List<OzelAmac> ozelAmacBEPteSecilen14) {
		this.ozelAmacBEPteSecilen14 = ozelAmacBEPteSecilen14;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen15() {
		return ozelAmacBEPteSecilen15;
	}

	public void setOzelAmacBEPteSecilen15(List<OzelAmac> ozelAmacBEPteSecilen15) {
		this.ozelAmacBEPteSecilen15 = ozelAmacBEPteSecilen15;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen16() {
		return ozelAmacBEPteSecilen16;
	}

	public void setOzelAmacBEPteSecilen16(List<OzelAmac> ozelAmacBEPteSecilen16) {
		this.ozelAmacBEPteSecilen16 = ozelAmacBEPteSecilen16;
	}

	public List<OzelAmac> getOzelAmacBEPteSecilen17() {
		return ozelAmacBEPteSecilen17;
	}

	public void setOzelAmacBEPteSecilen17(List<OzelAmac> ozelAmacBEPteSecilen17) {
		this.ozelAmacBEPteSecilen17 = ozelAmacBEPteSecilen17;
	}

	public TestDataService getTestDataService() {
		return testDataService;
	}

	public void setTestDataService(TestDataService testDataService) {
		this.testDataService = testDataService;
	}

	public BireyselEgitimPlaniService getBireyselEgitimPlaniService() {
		return bireyselEgitimPlaniService;
	}

	public void setBireyselEgitimPlaniService(BireyselEgitimPlaniService bireyselEgitimPlaniService) {
		this.bireyselEgitimPlaniService = bireyselEgitimPlaniService;
	}
	
	public String getOgrenciDogum() {
		return ogrenciDogum;
	}

	public void setOgrenciDogum(String ogrenciDogum) {
		this.ogrenciDogum = ogrenciDogum;
	}

	public void openPdf() throws IOException {
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    externalContext.redirect(pdffilePath);
	}
	
	public void PDFcreators() throws DocumentException, IOException {

		ozelAmacEvetVerilen = bireyselEgitimPlaniService.BEPEvetVerilenCevaplariGetir(dersId, ogrenciId);

		for (OzelAmac ozelAmac : ozelAmacEvetVerilen) {
			if (ozelAmac.getGenelAmac().getId() == genelAmac.getId()) {
				ozelAmacEvetVerilen1.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac2.getId()) {
				ozelAmacEvetVerilen2.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac3.getId()) {
				ozelAmacEvetVerilen3.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac4.getId()) {
				ozelAmacEvetVerilen4.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac5.getId()) {
				ozelAmacEvetVerilen5.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac6.getId()) {
				ozelAmacEvetVerilen6.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac7.getId()) {
				ozelAmacEvetVerilen7.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac8.getId()) {
				ozelAmacEvetVerilen8.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac9.getId()) {
				ozelAmacEvetVerilen9.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac10.getId()) {
				ozelAmacEvetVerilen10.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac11.getId()) {
				ozelAmacEvetVerilen11.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac12.getId()) {
				ozelAmacEvetVerilen12.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac13.getId()) {
				ozelAmacEvetVerilen13.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac14.getId()) {
				ozelAmacEvetVerilen14.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac15.getId()) {
				ozelAmacEvetVerilen15.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac16.getId()) {
				ozelAmacEvetVerilen16.add(ozelAmac);
			} else if (ozelAmac.getGenelAmac().getId() == genelAmac17.getId()) {
				ozelAmacEvetVerilen17.add(ozelAmac);
			}
		}

		Document document = new Document(PageSize.A4.rotate(), 50f, 40f, 50f, 40f);

		String jbosslocation = System.getProperty("jboss.home.dir");

		BaseFont baseTimes = BaseFont.createFont(
				"c://TimesNewRoman.ttf",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

		com.itextpdf.text.Font font = new com.itextpdf.text.Font(baseTimes, 12);

		PdfPTable table = new PdfPTable(new float[] { 3, 5, 1.5f, 1.5f });
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(new Phrase("Uzun Dönem Amaçlar", font));
		table.addCell(new Phrase("Kısa Dönem Amaçlar", font));
		table.addCell(new Phrase("Başlangıç-Bitiş Tarihi", font));
		table.addCell(new Phrase("Sorumlu Kisiler", font));

		PdfPCell[] cells = table.getRow(0).getCells();
		for (int j = 0; j < cells.length; j++) {
			cells[j].setBackgroundColor(BaseColor.GRAY);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		}
		for (int i = 0; i < ozelAmacEvetVerilen1.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen1.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);
						
			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen1.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen1.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen1.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen2.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen2.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen2.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen2.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen2.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen3.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen3.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);
			
			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen3.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen3.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen3.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen4.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen4.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen4.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen4.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen4.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}

		for (int i = 0; i < ozelAmacEvetVerilen5.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen5.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen5.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen5.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen5.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen6.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen6.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen6.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen6.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen6.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen7.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen7.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen7.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen7.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen7.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen8.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen8.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen8.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen8.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen8.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen9.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen9.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen9.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen9.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen9.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen10.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen10.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen10.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen10.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen10.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen11.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen11.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen11.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen11.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen11.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen12.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen12.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen12.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen12.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen12.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen13.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen13.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen13.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen13.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen13.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen14.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen14.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen14.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen14.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen14.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen15.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen15.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen15.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen15.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen15.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen16.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen16.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen16.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen16.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen16.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}
		for (int i = 0; i < ozelAmacEvetVerilen17.size(); i++) {
			List<Kazanim> kazanimlar = new ArrayList<>();
			kazanimlar.addAll(ozelAmacEvetVerilen17.get(i).getKazanimlar());
			
			Collections.sort(kazanimlar);

			PdfPCell pcell = new PdfPCell(new Phrase("Özel "+ozelAmacEvetVerilen17.get(i).toString(), font));
			pcell.setRowspan(ozelAmacEvetVerilen17.get(i).getKazanimlar().size());
			table.addCell(pcell);
			
			for (int j = 0; j < ozelAmacEvetVerilen17.get(i).getKazanimlar().size()  ; j++) {
					table.addCell(new Phrase("Kazanım "+kazanimlar.get(j).getIcerik(), font));
					table.addCell("");
					table.addCell("");
			}
		}

		String userHomePath = System.getProperty("user.home");
//		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
//				userHomePath + "\\Desktop" + "\\" + ogrenciAd + " " + ogrenciSoyad + " " + dersAd + " BEP.pdf"));

		String createdFilePath = System.getProperty("jboss.home.dir")+"\\pdfs" + "\\" + ogrenciAd  + ogrenciSoyad +"_"+ dersAd +"_"+ sinif +"_BEP.pdf";
		pdffilePath = createdFilePath;
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(createdFilePath));
		
		document.open();

		Paragraph header = new Paragraph("BİREYSELLEŞTİRİLMİŞ EĞİTİM PROGRAMI FORMU", font);
		header.setAlignment(Element.ALIGN_CENTER);
		document.add(header);

		Chunk glue = new Chunk(new VerticalPositionMark());
		Paragraph p = new Paragraph("Öğrencinin Adı Soyadı : " + ogrenciAd + " " + ogrenciSoyad, font);
		p.add(new Chunk(glue));
		p.add("Sınıfı/Numarası :" + getSinif().toString() + "/" + getOgrencino());
		document.add(p);

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);

		Chunk glue2 = new Chunk(new VerticalPositionMark());
		Paragraph p2 = new Paragraph("Eğitim Programını Hazırlayanlar : " + mySessionScopedBean.getFirstname() + " "
				+ mySessionScopedBean.getLastname(), font);
		p2.add(new Chunk(glue2));
		p2.add("BEP Hazırlama Tarihi : " + strDate);
		document.add(p2);

		document.add(new Paragraph(" "));

		document.add(table);

		document.add(new Paragraph(" "));

		Paragraph forDate = new Paragraph("..../..../........                           ");
		forDate.setAlignment(Element.ALIGN_RIGHT);
		document.add(forDate);

		PdfPTable tableSignatures = new PdfPTable(5);
		tableSignatures.setWidthPercentage(100);
		tableSignatures.addCell(getCell("Öğrenci Velisi", PdfPCell.ALIGN_CENTER, font));
		tableSignatures.addCell(getCell("Sınıf/Sınıf Rehber Öğretmeni ", PdfPCell.ALIGN_CENTER, font));
		tableSignatures.addCell(getCell("Branş Öğretmeni ", PdfPCell.ALIGN_CENTER, font));
		tableSignatures.addCell(getCell("Rehber Öğretmen ", PdfPCell.ALIGN_CENTER, font));
		tableSignatures.addCell(getCell("Birim Başkanı ", PdfPCell.ALIGN_CENTER, font));

		document.add(tableSignatures);

		document.close();

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("BEP Kaydedildi"));
		String filepaths =  ogrenciAd  + ogrenciSoyad +"_"+ dersAd +"_"+ sinif +"_BEP.pdf";
		System.out.println("PDFServlet?pdfPath="+filepaths);
		FacesContext.getCurrentInstance().getExternalContext().redirect("PDFServlet?pdfPath="+filepaths);
		
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("BEP Masaüstü'ne Kaydedildi"));
//
//		final String userHomePath2 = System.getProperty("user.home");
//		/** The original PDF file. */
//		final String COVER
//	        = userHomePath + "\\Desktop\\BEP.pdf";
//
//	    /** The resulting PDF file. */
//	    final String DEST
//	    = userHomePath + "\\Desktop\\"+ogrenciAd + " " + ogrenciSoyad + " " + dersAd +" BEPkapak.pdf";
//		
//	    //Create PdfReader instance.
//	    PdfReader pdfReader = 
//			new PdfReader(COVER);	
//
//	    //Create PdfStamper instance.
//	    PdfStamper pdfStamper = new PdfStamper(pdfReader,
//		new FileOutputStream(DEST));
//	    
//	    /*
//	     * 1.SAYFA BASKI
//	     */
//		//Contain the pdf data.
//		PdfContentByte p1 = 
//				pdfStamper.getOverContent(1);
//
//		p1.beginText();
//		//Set text font and size.
//		p1.setFontAndSize(baseTimes, 12);
//		
//		p1.setTextMatrix(205,748);
//		//Write text
//		p1.showText("Ümraniye");
//		p1.endText();
//		
//		// Okul adı
//		PdfContentByte p3 = 
//				pdfStamper.getOverContent(1);
//
//		p3.beginText();
//		//Set text font and size.
//		p3.setFontAndSize(baseTimes, 12);
//		
//		p3.setTextMatrix(178,707);
//		//Write text
//		p3.showText("Şakire Hanım Özel Eğitim Uygulama ");
//		p3.endText();
//	    
//		
//		// Öğrenci bilgileri
//				PdfContentByte p4 = 
//						pdfStamper.getOverContent(1);
//
//				p4.beginText();
//				//Set text font and size.
//				p4.setFontAndSize(baseTimes, 12);
//				
//				p4.setTextMatrix(158,590);
//				//Write text
//				p4.showText(ogrenciAd+ " "+ogrenciSoyad);
//				p4.endText();
//			    
//		// Öğrenci doğum bilgisi
//				PdfContentByte p5 = 
//						pdfStamper.getOverContent(1);
//
//				p5.beginText();
//				//Set text font and size.
//				p5.setFontAndSize(baseTimes, 12);
//				
//				p5.setTextMatrix(258,590);
//				//Write text
//				if(ogrenciDogum == null){
//					p5.showText(" ");
//				}else{
//					p5.showText(ogrenciDogum);
//				}
//				p5.endText();
//		
//		
//
//	    //Close the pdfStamper.
//	    pdfStamper.close();	

		
		
		
		
	}

	public PdfPCell getCell(String text, int alignment, com.itextpdf.text.Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setPadding(0);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

}
