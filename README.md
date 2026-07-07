# 🌐 TestAutomationWithPlaywrightJava Projesi

Bu repository, **Java + Playwright** kullanılarak geliştirilmiş kapsamlı bir **modern web test otomasyonu** projesidir.
 Proje; temel tarayıcı etkileşimlerinden başlayarak ileri seviye UI otomasyonu, hibrit raporlama sistemleri, ekran kaydı, dinamik bekleme stratejileri, CI/CD entegrasyonu ve profesyonel test mimarilerine kadar geniş kapsamlı otomasyon senaryoları içermektedir.
 
 Testler; Playwright framework'ünün modern otomasyon yaklaşımını öğretmek amacıyla aşamalı şekilde yapılandırılmıştır.
 Her sınıf belirli bir Playwright özelliğini veya otomasyon tekniğini derinlemesine ele alacak şekilde tasarlanmıştır.

 ---
 
 # 🎯 Projenin Amacı
 
 - Playwright framework'ünün modern kullanımını öğrenmek
 - Java ile profesyonel UI test otomasyonu geliştirmek
 - Dinamik locator stratejilerini uygulamak
 - Wait strategies (bekleme stratejileri) mantığını kavramak
 - Screenshot, video recording ve PDF üretimini öğrenmek
 - Allure Report + Extent Reports hibrit raporlama yapısını uygulamak
 - CI/CD süreçlerinde Playwright testlerini çalıştırmak
 - Gerçek test mimarileri ve reusable yapı oluşturmak
 - BaseTest yaklaşımı ile sürdürülebilir framework mantığını öğrenmek
 
 ---

# 🛠️ Kullanılan Teknolojiler ve Araçlar

| Teknoloji | Açıklama |
|----------|----------|
| **Java 17** | Test otomasyon dili |
| **Playwright 1.49.0** | Modern web otomasyon framework'ü |
| **JUnit 5** | Test runner ve assertion yapısı |
| **Allure Report** | Gelişmiş test raporlama sistemi |
| **Extent Reports** | HTML tabanlı görsel raporlama |
| **JavaFaker** | Dinamik test verisi üretimi |
| **SLF4J & Logger** | Loglama sistemi |
| **Maven** | Dependency ve build yönetimi |
| **GitHub Actions** | CI/CD otomasyon süreci |
| **IntelliJ IDEA** | Geliştirme ortamı (IDE) |

---

# 📁 Proje Yapısı

```
TestAutomationWithPlaywrightJava/
├── .github/workflows/
│   └── playwright-ci.yml      # CI/CD GitHub Actions yapılandırması
├── src/test/java/
│   ├── testInputAndOutput/    # Test sırasında üretilen PDF, Screenshot ve Loglar
│   │   ├── P12_testOtomasyonuFullPage.pdf
│   │   ├── P12_testOtomasyonuFullPage.pdf
│   │   ├── P12_testOtomasyonuFullPage.pdf
│   │   └── testOtomasyonuPage_logo.png
│   ├── utilities/             # Yardımcı sınıflar
│   │   ├── BaseTest.java      # Test öncesi/sonrası kurulumlar (Setup/Teardown)
│   │   └── ExtentManager.java # Raporlama yöneticisi
│   ├── C01_AutoClosure.java   # Otomatik tarayıcı kapatma
│   ├── C02_ManualClosure.java # Manuel tarayıcı kapatma
│   ├── C03_ScreenSize.java    # Ekran boyutu işlemleri
│   ├── C04_NavigationMethods.java # Navigasyon (back, forward, reload)
│   ├── C05_ElementMethods.java # Element metotları
│   ├── C06_ElementMethods.java # Element metotları
│   ├── C07_PageCheckBox.java  # Checkbox etkileşimleri
│   ├── C08_PageCheckBox_Uncheck.java # Checkbox kaldırma işlemleri
│   ├── C09_PageSelect.java    # Select dropdown işlemleri
│   ├── C10_TextContent_innerText_innerHTML.java # Text içerik işlemleri
│   ├── C11_ElementBilgisiAlma_isVisible_isChecked.java # Element bilgi alma işlemleri
│   ├── C12_Screenshot_Pdf.java # Ekran görüntüsü ve PDF alma
│   ├── C13_BuiltInLocators.java # Built-in locator kullanımları
│   ├── C14_OtherLocators.java # Diğer locator türleri
│   ├── C15_PageAssertions.java # Page assertion işlemleri
│   ├── C16_LocatorAssertions.java # Locator assertion işlemleri
│   ├── C17_Iframe.java        # Iframe yönetimi
│   ├── C18_Actions.java       # Mouse ve keyboard aksiyonları
│   ├── C19_DragAndDrop.java   # Sürükle ve bırak işlemleri
│   ├── C20_Dropdown.java      # Dropdown işlemleri
│   ├── C21_BaseTestKullanimi.java # BaseTest kullanımı
│   ├── C22_BaseTestKullanimi_ExtentReport.java # Extent Report entegrasyonu
│   ├── C23_BaseTestKullanimi_AllureReport.java # Allure Report entegrasyonu
│   ├── C24_SingleFileUpload.java # Tekli dosya yükleme testleri
│   ├── C25_MultipleFileUpload.java # Çoklu dosya yükleme testleri
│   ├── C26_MultipleWindow.java # Çoklu pencere yönetimi
│   ├── C27_ScreenRecord.java  # Ekran kaydı işlemleri
│   ├── C28_ScreenRecord_AllureReportV2.java # Screen record + Allure raporu
│   ├── C29_AutomationExerciseSignUp.java # Automation Exercise signup testi
│   ├── C30_AutomationExerciseSignUp_AllureReportV2.java # Allure Raporu entegrasyonu
│   ├── C31_Scroll_AllureReportV1.java # Scroll işlemleri ve Allure raporu
│   ├── C32_Scroll_AllureReportV2.java # Scroll işlemleri ve gelişmiş Allure raporu
│   ├── C33_WaitStrategies_AllureReportV1.java # Dinamik bekleme stratejileri
│   ├── C34_WaitStrategies_AllureReportV2.java # Gelişmiş wait stratejileri
│   └── C35_LocatorMasterNotes.java # Locator master notları
├── .gitignore                 # Takip edilmeyecek dosyalar
├── pom.xml                    # Maven bağımlılıkları ve pluginler
└── README.md
```
---

# 📂 Müfredat ve Sınıf Yapısı (C01 - C35)
 
 Proje, 35 farklı sınıfta Playwright otomasyonunun kritik konularını kapsamaktadır:
 
 | Seviye | Konular | Örnek Sınıflar |
 |----------|----------|----------|
 | **Temel UI** | Browser yönetimi, navigation, auto/manual closure | C01 - C04 |
 | **Element İşlemleri** | Locator stratejileri, assertions, dropdown, checkbox | C05 - C20 |
 | **İleri Seviye UI** | Iframe, multiple window, drag & drop, upload | C17 - C26 |
 | **Raporlama & Medya** | Allure, Extent Report, screenshot, video recording | C22 - C32 |
 | **Framework Stratejileri** | Wait strategies, BaseTest yapısı, scroll teknikleri | C31 - C35 |
 
 ---

 # 🎯 Kapsanan Otomasyon Konuları
 ## 🗓️ Temel Seviye Etkileşimler
 
 | Konu | Açıklama |
 |----------|----------|
 | Navigation | Sayfa geçişleri, refresh, back-forward işlemleri |
 | Locators | CSS, XPath ve Playwright built-in locator kullanımları |
 | Assertions | Page ve Locator doğrulamaları |
 | Element Actions | Click, fill, hover, select işlemleri |
 
 ---

## 🗓️ İleri Seviye UI İşlemleri

| Konu | Açıklama |
|----------|----------|
| Iframe & Windows | Çoklu sekme ve iframe yönetimi |
| Actions | Drag & Drop, keyboard ve mouse aksiyonları |
| File Operations | Tekli/çoklu dosya yükleme işlemleri |
| Wait Strategies | Auto-waiting, explicit wait, dynamic waits |
| Scroll Operations | Sayfa scroll stratejileri |

---

## 🗓️ Raporlama ve Medya Yönetimi

| Konu | Açıklama |
|----------|----------|
| Allure Report | Modern step-based raporlama |
| Extent Reports | HTML dashboard raporları |
| Screen Recording | Test video kayıt sistemi |
| Screenshot | Full-page ve element screenshot işlemleri |
| PDF Generation | Sayfa çıktılarının PDF olarak alınması |

---

# 🏗️ Mimari ve Evrimsel Gelişim (Evolutionary Design)

Bu projeyi farklılaştıran temel noktalardan biri; framework mimarisinin zaman içinde evrimsel olarak geliştirilmiş olmasıdır.

### 1. BaseTest Stratejisi
*   V1 (Başlangıç): Toolkit ile ekran boyutu alma (Basık ekran ve piksel kaybı sorunları içerir).
*   V2 (Profesyonel): BrowserContext izolasyonu, --start-maximized argümanı ve setViewportSize(null) ile tam uyumlu ekran ölçekleme.
*   CI/CD Uyumu: Ortama göre otomatik Headless (GitHub Actions) veya Headed (Lokal) mod değişimi.

### V2 (Profesyonel Yaklaşım)
- BrowserContext izolasyonu
- `--start-maximized` desteği
- `setViewportSize(null)` kullanımı
- Daha stabil ekran ölçekleme sistemi

### CI/CD Uyumlu Mimari
- Lokal ortamda headed mode
- GitHub Actions ortamında headless mode
- Ortama göre otomatik browser stratejisi

---

## 2. Hibrit Raporlama Sistemi

Proje aynı anda birden fazla raporlama sistemini desteklemektedir:

### Allure Report V1
- @Step anotasyonları
- Klasik step tabanlı raporlama

### Allure Report V2
- `Allure.step()` lambda yaklaşımı
- Nested reporting yapısı
- Daha okunabilir modern raporlama sistemi

### Extent Reports
- HTML dashboard yapısı
- Görsel raporlama ekranı
- Test summary sistemi

### Logger Sistemi
- Konsol üzerinden gerçek zamanlı loglama
- Hata ayıklama kolaylığı

---
# 🔄 CI/CD Süreci (GitHub Actions)

Proje GitHub Actions ile otomatik test pipeline yapısına sahiptir.

CI/CD sürecinde:

- Ubuntu ortamında Java kurulumu yapılır
- Maven cache optimize edilir
- Chromium browser otomatik yüklenir
- Tüm Playwright testleri çalıştırılır
- Allure raporları otomatik oluşturulur
- GitHub Pages üzerinden canlı rapor yayınlanır

---

# 📊 Test Otomasyon Raporu

Son test sonuçlarını görmek için aşağıdaki Allure raporunu inceleyebilirsiniz:

[![Allure Report](https://img.shields.io/badge/Allure%20Report-Live-green?style=for-the-badge&logo=allure)](https://kilicceren.github.io/TestAutomationWithPlaywrightJava/allure-history/)

---

# ▶️ Projeyi Çalıştırma Adımları

## Ön Gereksinimler

- Java JDK 17 veya üzeri
- Maven kurulu olmalıdır

---

## Kurulum

Repository'i klonlayın:
 
 ```bash
 git clone https://github.com/kilicCeren/TestAutomationWithPlaywrightJava.git
 ```

Bağımlılıkları yükleyin:

```bash
mvn clean install
```
## Testleri Çalıştırma

Tüm testleri çalıştırmak için:

```bash
mvn test
```
Belirli bir test sınıfını çalıştırmak için:

```bash
mvn test -Dtest=C30_AutomationExerciseSignUp_AllureReportV2
```

---
