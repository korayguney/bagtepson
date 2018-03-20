package com.bagtep.business;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bagtep.domain.Ders;
import com.bagtep.domain.GenelAmac;
import com.bagtep.domain.Kazanim;
import com.bagtep.domain.KazanimEK;
import com.bagtep.domain.Ogrenci;
import com.bagtep.domain.OzelAmac;
import com.bagtep.domain.OzelAmacEK;
import com.bagtep.domain.Sinif;
import com.bagtep.domain.User;
import com.bagtep.utils.Utilities;

@Stateless
public class TestDataService {

	@PersistenceContext
	private EntityManager entityManager;

	public void createTestData() {
		final long start = System.nanoTime();

		Kazanim k = entityManager.find(Kazanim.class, 1);
		if (k == null) {
			System.out.println("Hiç veri yok, yenisi giriliyor");
			GenelAmac genelAmac = null;
			OzelAmac ozelAmac = null;
			Kazanim kazanim = null;

			// GenelAmacEK genelAmacEK = null;
			OzelAmacEK ozelAmacEK = null;
			KazanimEK kazanimEK = null;

			User u1 = new User("Koray", "Güney", "koray", "1234", "Admin");
			User u2 = new User("Zeki", "Çelik", "zeki", "1234", "Admin");
			User u3 = new User("Ahmet", "Demirelli", "ahmet", "1234", "Admin");
			// User u4 = new User("Bülent", "Dertli", "bulent", "1234",
			// "Öğretmen");
			// User u5 = new User("Ulaş", "Yirmi", "ulas", "1234", "Öğretmen");
			// User u6 = new User("Merve", "Tosun", "merve", "1234",
			// "Öğretmen");
			// User u7 = new User("Ali", "Bektaş", "ali", "1234", "Öğretmen");
			// User u8 = new User("Veli", "Çakır", "veli", "1234", "Öğretmen");
			// User u9 = new User("Serkan", "Doğuş", "serkan", "1234",
			// "Öğretmen");

			u1.setPassword(Utilities.hashPassword(u1.getPassword()));
			u2.setPassword(Utilities.hashPassword(u2.getPassword()));
			u3.setPassword(Utilities.hashPassword(u3.getPassword()));
			// u4.setPassword(Utilities.hashPassword(u4.getPassword()));
			// u5.setPassword(Utilities.hashPassword(u5.getPassword()));
			// u6.setPassword(Utilities.hashPassword(u6.getPassword()));
			// u7.setPassword(Utilities.hashPassword(u7.getPassword()));
			// u8.setPassword(Utilities.hashPassword(u8.getPassword()));
			// u9.setPassword(Utilities.hashPassword(u9.getPassword()));

			Sinif sinif1 = new Sinif("1-A");
			Sinif sinif2 = new Sinif("1-B");
			Sinif sinif3 = new Sinif("2-A");
			Sinif sinif4 = new Sinif("2-B");
			Sinif sinif5 = new Sinif("3-A");
			Sinif sinif6 = new Sinif("3-B");
			Sinif sinif7 = new Sinif("4-A");
			Sinif sinif8 = new Sinif("4-B");
			Sinif sinif9 = new Sinif("5-A");
			Sinif sinif10 = new Sinif("5-B");

			Ogrenci ogrenci1 = new Ogrenci("Leyla", "Şeker", 111, "Kız", "Veli", "Kurtuluş", "05305554433",
					"Tuzla/İstanbul", sinif1);
			Ogrenci ogrenci2 = new Ogrenci("Ayşe", "Lale", 222, "Kız", "Ali", "Kurtuluş", "05305554444",
					"Kartal/İstanbul", sinif1);
			Ogrenci ogrenci3 = new Ogrenci("Fatma", "Çakmak", 333, "Kız", "Remzi", "Kurtuluş", "05305554455",
					"Pendik/İstanbul", sinif1);
			Ogrenci ogrenci4 = new Ogrenci("Eren", "Tarım", 444, "Erkek", "Haydar", "Kurtuluş", "05305554466",
					"Ataşehir/İstanbul", sinif1);
			Ogrenci ogrenci5 = new Ogrenci("Şakir", "Parmak", 555, "Erkek", "Kemal", "Kurtuluş", "05305554477",
					"Kadıköy/İstanbul", sinif2);
			Ogrenci ogrenci6 = new Ogrenci("Bülent", "Gerçek", 666, "Erkek", "Behçet", "Kurtuluş", "05305554488",
					"Üsküdar/İstanbul", sinif2);
			Ogrenci ogrenci7 = new Ogrenci("Elif", "Demir", 777, "Kız", "Şakir", "Kurtuluş", "05305554488",
					"Üsküdar/İstanbul", sinif2);
			Ogrenci ogrenci8 = new Ogrenci("Oğuzhan", "Yılmaz", 888, "Erkek", "Serdar", "Kurtuluş", "05305554488",
					"Üsküdar/İstanbul", sinif3);
			Ogrenci ogrenci9 = new Ogrenci("Serdar", "Güngör", 999, "Erkek", "Fatma", "Kurtuluş", "05305554488",
					"Üsküdar/İstanbul", sinif3);
			Ogrenci ogrenci10 = new Ogrenci("Behçet", "Sakaryalı", 123, "Erkek", "Oğuzhan", "Kurtuluş", "05305554488",
					"Üsküdar/İstanbul", sinif4);

			// Ogretmen ogretmen1 = new Ogretmen("Bülent", "Dertli");
			// Ogretmen ogretmen2 = new Ogretmen("Ulaş", "Yirmi");
			// Ogretmen ogretmen3 = new Ogretmen("Merve", "Tosun");
			// Ogretmen ogretmen4 = new Ogretmen("Ali", "Bektaş");
			// Ogretmen ogretmen5 = new Ogretmen("Veli", "Çakır");
			// Ogretmen ogretmen6 = new Ogretmen("Serkan", "Doğuş");

			Ders matematik = new Ders("Matematik");
			Ders dilvekonusma = new Ders("Dil ve Konuşma Gelişimi");
			Ders dinkulturu = new Ders("Din Kültürü ve Ahlak Bilgisi");
			Ders hayatbilgisi = new Ders("Hayat Bilgisi");
			Ders muzik = new Ders("Müzik");
			Ders beslenme = new Ders("Beslenme Bilgisi");
			Ders gorselsanat = new Ders("Görsel Sanatlar");
			Ders okumayazma = new Ders("Okuma Yazma");
			Ders trafik = new Ders("Trafik ve İlk Yardım Eğitimi");
			Ders bedenegitimi = new Ders("Beden Eğitimi");
			Ders toplumsaluyum = new Ders("Toplumsal Uyum Becerileri");

			// Dersleri KAYDET

			entityManager.persist(matematik);
			entityManager.persist(dilvekonusma);
			entityManager.persist(dinkulturu);
			entityManager.persist(muzik);
			entityManager.persist(hayatbilgisi);
			entityManager.persist(beslenme);
			entityManager.persist(gorselsanat);
			entityManager.persist(okumayazma);
			entityManager.persist(trafik);
			entityManager.persist(bedenegitimi);
			entityManager.persist(toplumsaluyum);

			// Kullanıcıları KAYDET

			entityManager.persist(u1);
			entityManager.persist(u2);
			entityManager.persist(u3);
			// entityManager.persist(u4);
			// entityManager.persist(u5);
			// entityManager.persist(u6);
			// entityManager.persist(u7);
			// entityManager.persist(u8);
			// entityManager.persist(u9);

			// Sınıf KAYDET

			entityManager.persist(sinif1);
			entityManager.persist(sinif2);
			entityManager.persist(sinif3);
			entityManager.persist(sinif4);
			entityManager.persist(sinif5);
			entityManager.persist(sinif6);
			entityManager.persist(sinif7);
			entityManager.persist(sinif8);
			entityManager.persist(sinif9);
			entityManager.persist(sinif10);

			// Öğrenci KAYDET

			entityManager.persist(ogrenci1);
			entityManager.persist(ogrenci2);
			entityManager.persist(ogrenci3);
			entityManager.persist(ogrenci4);
			entityManager.persist(ogrenci5);
			entityManager.persist(ogrenci6);
			entityManager.persist(ogrenci7);
			entityManager.persist(ogrenci8);
			entityManager.persist(ogrenci9);
			entityManager.persist(ogrenci10);

			// Öğretmen KAYDET

			// entityManager.persist(ogretmen1);
			// entityManager.persist(ogretmen2);
			// entityManager.persist(ogretmen3);
			// entityManager.persist(ogretmen4);
			// entityManager.persist(ogretmen5);
			// entityManager.persist(ogretmen6);

			try {
				// DİL VE KONU�?MA GELİ�?İMİ DERSİ VERİLERİ GİRİLİYOR !!!
				InputStream in1 = getClass()
						.getResourceAsStream("/com/bagtep/domain/testdata/dilvekonusmagelisimiKD.txt");
				BufferedReader rd1 = new BufferedReader(new InputStreamReader(in1, "utf-8"));

				while (true) {
					String line = rd1.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = new GenelAmac(dilvekonusma, line);
						entityManager.persist(genelAmac);
					}
					if (line.contains("Amaç ")) {
						// FIXME Amaç puanlarını burada fetch ediyorum

						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote(" P)"));
						ozelAmac = new OzelAmac(genelAmac, lineWithoutPharantesis[0]);
						ozelAmac.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));

						entityManager.persist(ozelAmac);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanim = new Kazanim(line, ozelAmac);
						entityManager.persist(kazanim);
					}
				}

				// DİL VE KONU�?MA GELİ�?İMİ DERSİ EK VERİLERİ GİRİLİYOR !!!
				InputStream in1ek = getClass()
						.getResourceAsStream("/com/bagtep/domain/testdata/dilvekonusmagelisimiEK.txt");
				BufferedReader rd1ek = new BufferedReader(new InputStreamReader(in1ek, "utf-8"));

				while (true) {
					String line = rd1ek.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = (GenelAmac) entityManager
								.createQuery("select g from GenelAmac g where g.icerik=:icerik", GenelAmac.class)
								.setParameter("icerik", line).getSingleResult();
					}
					if (line.contains("Amaç ")) {
						// FIXME Amaç puanlarını burada fetch ediyorum

						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote(" P)"));
						ozelAmacEK = new OzelAmacEK(genelAmac, lineWithoutPharantesis[0]);
						ozelAmacEK.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));

						entityManager.persist(ozelAmacEK);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanimEK = new KazanimEK(line, ozelAmacEK);
						entityManager.persist(kazanimEK);
					}
				}

				// DİN KÜLTÜRÜ VE AHLAK BİLGİSİ DERSİ VERİLERİ GİRİLİYOR !!!
				InputStream in2 = getClass()
						.getResourceAsStream("/com/bagtep/domain/testdata/dinkulturuveahlakbilgisiKD.txt");
				BufferedReader rd2 = new BufferedReader(new InputStreamReader(in2, "utf-8"));

				while (true) {
					String line = rd2.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = new GenelAmac(dinkulturu, line);
						entityManager.persist(genelAmac);
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote(" P)"));
						ozelAmac = new OzelAmac(genelAmac, lineWithoutPharantesis[0]);
						ozelAmac.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmac);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanim = new Kazanim(line, ozelAmac);
						entityManager.persist(kazanim);
					}
				}

				// DİN KÜLTÜRÜ VE AHLAK BİLGİSİ DERSİ EK VERİLERİ GİRİLİYOR !!!
				InputStream in2ek = getClass()
						.getResourceAsStream("/com/bagtep/domain/testdata/dinkulturuveahlakbilgisiEK.txt");
				BufferedReader rd2ek = new BufferedReader(new InputStreamReader(in2ek, "utf-8"));

				while (true) {
					String line = rd2ek.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = (GenelAmac) entityManager
								.createQuery("select g from GenelAmac g where g.icerik=:icerik", GenelAmac.class)
								.setParameter("icerik", line).getSingleResult();
					}
					if (line.contains("Amaç ")) {
						// FIXME Amaç puanlarını burada fetch ediyorum

						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote(" P)"));
						ozelAmacEK = new OzelAmacEK(genelAmac, lineWithoutPharantesis[0]);
						ozelAmacEK.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));

						entityManager.persist(ozelAmacEK);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanimEK = new KazanimEK(line, ozelAmacEK);
						entityManager.persist(kazanimEK);
					}
				}

				// HAYAT BİLGİSİ DERSİ VERİLERİ GİRİLİYOR !!!
				InputStream in3 = getClass().getResourceAsStream("/com/bagtep/domain/testdata/hayatbilgisiKD.txt");
				BufferedReader rd3 = new BufferedReader(new InputStreamReader(in3, "utf-8"));

				while (true) {
					String line = rd3.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = new GenelAmac(hayatbilgisi, line);
						entityManager.persist(genelAmac);
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						ozelAmac = new OzelAmac(genelAmac, lineWithoutPharantesis[0]);

						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmac.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmac);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanim = new Kazanim(line, ozelAmac);
						entityManager.persist(kazanim);
					}
				}

				// HAYAT BİLGİSİ DERSİ EK VERİLERİ GİRİLİYOR !!!
				InputStream in3ek = getClass().getResourceAsStream("/com/bagtep/domain/testdata/hayatbilgisiEK.txt");
				BufferedReader rd3ek = new BufferedReader(new InputStreamReader(in3ek, "utf-8"));

				while (true) {
					String line = rd3ek.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = (GenelAmac) entityManager
								.createQuery("select g from GenelAmac g where g.icerik=:icerik", GenelAmac.class)
								.setParameter("icerik", line).getSingleResult();
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						ozelAmacEK = new OzelAmacEK(genelAmac, lineWithoutPharantesis[0]);

						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmacEK.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmacEK);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanimEK = new KazanimEK(line, ozelAmacEK);
						entityManager.persist(kazanimEK);
					}
				}
				// MÜZİK DERSİ VERİLERİ GİRİLİYOR !!!
				InputStream in4 = getClass().getResourceAsStream("/com/bagtep/domain/testdata/muzikKD.txt");
				BufferedReader rd4 = new BufferedReader(new InputStreamReader(in4, "utf-8"));

				while (true) {
					String line = rd4.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = new GenelAmac(muzik, line);
						entityManager.persist(genelAmac);
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmac = new OzelAmac(genelAmac, lineWithoutPharantesis[0]);
						ozelAmac.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmac);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanim = new Kazanim(line, ozelAmac);
						entityManager.persist(kazanim);
					}
				}

				// MÜZİK DERSİ EK VERİLERİ GİRİLİYOR !!!
				InputStream in4ek = getClass().getResourceAsStream("/com/bagtep/domain/testdata/muzikEK.txt");
				BufferedReader rd4ek = new BufferedReader(new InputStreamReader(in4ek, "utf-8"));

				while (true) {
					String line = rd4ek.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = (GenelAmac) entityManager
								.createQuery("select g from GenelAmac g where g.icerik=:icerik", GenelAmac.class)
								.setParameter("icerik", line).getSingleResult();
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmacEK = new OzelAmacEK(genelAmac, lineWithoutPharantesis[0]);
						ozelAmacEK.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmacEK);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanimEK = new KazanimEK(line, ozelAmacEK);
						entityManager.persist(kazanimEK);
					}
				}

				// BESLENME DERSİ VERİLERİ GİRİLİYOR !!!
				InputStream in5 = getClass().getResourceAsStream("/com/bagtep/domain/testdata/beslenmeKD.txt");
				BufferedReader rd5 = new BufferedReader(new InputStreamReader(in5, "utf-8"));

				while (true) {
					String line = rd5.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = new GenelAmac(beslenme, line);
						entityManager.persist(genelAmac);
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmac = new OzelAmac(genelAmac, lineWithoutPharantesis[0]);
						ozelAmac.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmac);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanim = new Kazanim(line, ozelAmac);
						entityManager.persist(kazanim);
					}
				}

				// BESLENME DERSİ EK VERİLERİ GİRİLİYOR !!!
				InputStream in5ek = getClass().getResourceAsStream("/com/bagtep/domain/testdata/beslenmeEK.txt");
				BufferedReader rd5ek = new BufferedReader(new InputStreamReader(in5ek, "utf-8"));

				while (true) {
					String line = rd5ek.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = (GenelAmac) entityManager
								.createQuery("select g from GenelAmac g where g.icerik=:icerik", GenelAmac.class)
								.setParameter("icerik", line).getSingleResult();
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmacEK = new OzelAmacEK(genelAmac, lineWithoutPharantesis[0]);
						ozelAmacEK.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmacEK);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanimEK = new KazanimEK(line, ozelAmacEK);
						entityManager.persist(kazanimEK);
					}
				}

				// GÖRSEL SANATLAR VERİLERİ GİRİLİYOR !!!
				InputStream in6 = getClass().getResourceAsStream("/com/bagtep/domain/testdata/gorselsanatlarKD.txt");
				BufferedReader rd6 = new BufferedReader(new InputStreamReader(in6, "utf-8"));

				while (true) {
					String line = rd6.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = new GenelAmac(gorselsanat, line);
						entityManager.persist(genelAmac);
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmac = new OzelAmac(genelAmac, lineWithoutPharantesis[0]);
						ozelAmac.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmac);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanim = new Kazanim(line, ozelAmac);
						entityManager.persist(kazanim);
					}
				}

				// GÖRSEL SANATLAR EK VERİLERİ GİRİLİYOR !!!
				InputStream in6ek = getClass().getResourceAsStream("/com/bagtep/domain/testdata/gorselsanatlarEK.txt");
				BufferedReader rd6ek = new BufferedReader(new InputStreamReader(in6ek, "utf-8"));

				while (true) {
					String line = rd6ek.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = (GenelAmac) entityManager
								.createQuery("select g from GenelAmac g where g.icerik=:icerik", GenelAmac.class)
								.setParameter("icerik", line).getSingleResult();
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmacEK = new OzelAmacEK(genelAmac, lineWithoutPharantesis[0]);
						ozelAmacEK.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmacEK);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanimEK = new KazanimEK(line, ozelAmacEK);
						entityManager.persist(kazanimEK);
					}
				}

				// OKUMA YAZMA DERSİ VERİLERİ GİRİLİYOR !!!
				InputStream in7 = getClass().getResourceAsStream("/com/bagtep/domain/testdata/okumayazmaKD.txt");
				BufferedReader rd7 = new BufferedReader(new InputStreamReader(in7, "utf-8"));

				while (true) {
					String line = rd7.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = new GenelAmac(okumayazma, line);
						entityManager.persist(genelAmac);
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmac = new OzelAmac(genelAmac, lineWithoutPharantesis[0]);
						ozelAmac.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmac);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanim = new Kazanim(line, ozelAmac);
						entityManager.persist(kazanim);
					}
				}

				// OKUMA YAZMA DERSİ EK VERİLERİ GİRİLİYOR !!!
				InputStream in7ek = getClass().getResourceAsStream("/com/bagtep/domain/testdata/okumayazmaEK.txt");
				BufferedReader rd7ek = new BufferedReader(new InputStreamReader(in7ek, "utf-8"));

				while (true) {
					String line = rd7ek.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = (GenelAmac) entityManager
								.createQuery("select g from GenelAmac g where g.icerik=:icerik", GenelAmac.class)
								.setParameter("icerik", line).getSingleResult();
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmacEK = new OzelAmacEK(genelAmac, lineWithoutPharantesis[0]);
						ozelAmacEK.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmacEK);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanimEK = new KazanimEK(line, ozelAmacEK);
						entityManager.persist(kazanimEK);
					}
				}

				// MATEMATİK DERSİ VERİLERİ GİRİLİYOR !!! (ASIL)
				InputStream in8 = getClass().getResourceAsStream("/com/bagtep/domain/testdata/matematikKD.txt");
				BufferedReader rd8 = new BufferedReader(new InputStreamReader(in8, "utf-8"));

				while (true) {
					String line = rd8.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = new GenelAmac(matematik, line);
						entityManager.persist(genelAmac);
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmac = new OzelAmac(genelAmac, lineWithoutPharantesis[0]);
						ozelAmac.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmac);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanim = new Kazanim(line, ozelAmac);
						entityManager.persist(kazanim);
					}
				}

				// MATEMATİK DERSİ EK VERİLERİ GİRİLİYOR !!! (ASIL)
				InputStream in8ek = getClass().getResourceAsStream("/com/bagtep/domain/testdata/matematikEK.txt");
				BufferedReader rd8ek = new BufferedReader(new InputStreamReader(in8ek, "utf-8"));

				while (true) {
					String line = rd8ek.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = (GenelAmac) entityManager
								.createQuery("select g from GenelAmac g where g.icerik=:icerik", GenelAmac.class)
								.setParameter("icerik", line).getSingleResult();
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmacEK = new OzelAmacEK(genelAmac, lineWithoutPharantesis[0]);
						ozelAmacEK.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmacEK);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanimEK = new KazanimEK(line, ozelAmacEK);
						entityManager.persist(kazanimEK);
					}
				}

				// TRAFİK VE İLKYARDIM DERSİ VERİLERİ GİRİLİYOR !!!
				InputStream in9 = getClass().getResourceAsStream("/com/bagtep/domain/testdata/trafikKD.txt");
				BufferedReader rd9 = new BufferedReader(new InputStreamReader(in9, "utf-8"));

				while (true) {
					String line = rd9.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = new GenelAmac(trafik, line);
						entityManager.persist(genelAmac);
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmac = new OzelAmac(genelAmac, lineWithoutPharantesis[0]);
						ozelAmac.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmac);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanim = new Kazanim(line, ozelAmac);
						entityManager.persist(kazanim);
					}
				}

				// TRAFİK VE İLKYARDIM EK DERSİ VERİLERİ GİRİLİYOR !!!
				InputStream in9ek = getClass().getResourceAsStream("/com/bagtep/domain/testdata/trafikEK.txt");
				BufferedReader rd9ek = new BufferedReader(new InputStreamReader(in9ek, "utf-8"));

				while (true) {
					String line = rd9ek.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = (GenelAmac) entityManager
								.createQuery("select g from GenelAmac g where g.icerik=:icerik", GenelAmac.class)
								.setParameter("icerik", line).getSingleResult();
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmacEK = new OzelAmacEK(genelAmac, lineWithoutPharantesis[0]);
						ozelAmacEK.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmacEK);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanimEK = new KazanimEK(line, ozelAmacEK);
						entityManager.persist(kazanimEK);
					}
				}

				// BEDEN E�?İTİMİ DERSİ VERİLERİ GİRİLİYOR !!!
				InputStream in10 = getClass().getResourceAsStream("/com/bagtep/domain/testdata/bedenegitimiKD.txt");
				BufferedReader rd10 = new BufferedReader(new InputStreamReader(in10, "utf-8"));

				while (true) {
					String line = rd10.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = new GenelAmac(bedenegitimi, line);
						entityManager.persist(genelAmac);
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmac = new OzelAmac(genelAmac, lineWithoutPharantesis[0]);
						ozelAmac.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmac);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanim = new Kazanim(line, ozelAmac);
						entityManager.persist(kazanim);
					}
				}

				// BEDEN E�?İTİMİ DERSİ EK VERİLERİ GİRİLİYOR !!!
				InputStream in10ek = getClass().getResourceAsStream("/com/bagtep/domain/testdata/bedenegitimiEK.txt");
				BufferedReader rd10ek = new BufferedReader(new InputStreamReader(in10ek, "utf-8"));

				while (true) {
					String line = rd10ek.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = (GenelAmac) entityManager
								.createQuery("select g from GenelAmac g where g.icerik=:icerik", GenelAmac.class)
								.setParameter("icerik", line).getSingleResult();
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmacEK = new OzelAmacEK(genelAmac, lineWithoutPharantesis[0]);
						ozelAmacEK.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmacEK);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanimEK = new KazanimEK(line, ozelAmacEK);
						entityManager.persist(kazanimEK);
					}
				}

				// TOPLUMSAL UYUM BECERİLERİ DERSİ VERİLERİ GİRİLİYOR !!!
				InputStream in11 = getClass().getResourceAsStream("/com/bagtep/domain/testdata/toplumsaluyumKD.txt");
				BufferedReader rd11 = new BufferedReader(new InputStreamReader(in11, "utf-8"));

				while (true) {
					String line = rd11.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = new GenelAmac(toplumsaluyum, line);
						entityManager.persist(genelAmac);
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmac = new OzelAmac(genelAmac, lineWithoutPharantesis[0]);
						ozelAmac.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmac);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanim = new Kazanim(line, ozelAmac);
						entityManager.persist(kazanim);
					}
				}

				// TOPLUMSAL UYUM BECERİLERİ EK DERSİ VERİLERİ GİRİLİYOR !!!
				InputStream in11ek = getClass().getResourceAsStream("/com/bagtep/domain/testdata/toplumsaluyumEK.txt");
				BufferedReader rd11ek = new BufferedReader(new InputStreamReader(in11ek, "utf-8"));

				while (true) {
					String line = rd11ek.readLine();
					if (line == null)
						break;

					if (line.contains("Genel Amaç")) {
						genelAmac = (GenelAmac) entityManager
								.createQuery("select g from GenelAmac g where g.icerik=:icerik", GenelAmac.class)
								.setParameter("icerik", line).getSingleResult();
					}
					if (line.contains("Amaç ")) {
						String[] lineWithoutPharantesis = line.split(Pattern.quote("("));
						String[] lineWithoutPharantesis2 = lineWithoutPharantesis[1].split(Pattern.quote("P)"));
						ozelAmacEK = new OzelAmacEK(genelAmac, lineWithoutPharantesis[0]);
						ozelAmacEK.setDegerlendirmePuani(Double.parseDouble(lineWithoutPharantesis2[0]));
						entityManager.persist(ozelAmacEK);
					}
					if (!line.contains("Genel Amaç") && !line.contains("Amaç ") && !line.contains("Davranış")
							&& line.length() > 5) {
						kazanimEK = new KazanimEK(line, ozelAmacEK);
						entityManager.persist(kazanimEK);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			final long end = System.nanoTime();
			System.out.println("Test data created in " + ((end - start) / 1000000) + "ms");
		} else {
			System.out.println("Test data already exist");
		}

	}

	public GenelAmac getGenelAmac(int dersId, int genelAmacId) {

		List<GenelAmac> genelAmaclar = entityManager.createQuery("SELECT g FROM GenelAmac g WHERE g.ders.id =:dersId")
				.setParameter("dersId", dersId).getResultList();

		GenelAmac genelAmac = genelAmaclar.get(genelAmacId - 1);
		for (OzelAmac o : genelAmac.getOzelAmaclar()) {
			for (Kazanim k : o.getKazanimlar()) {

			}
		}
		return genelAmac;
	}
}
