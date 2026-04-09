package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        // Rapor oluşturma sırasında oluşabilecek karakter/dil hatalarını önlemek için sistem dilini İngilizceye sabitliyoruz.
        java.util.Locale.setDefault(java.util.Locale.ENGLISH);

        if (extent == null) {
            // SparkReporter: Raporun görsel arayüzünü ve kaydedileceği yolu (path) belirler.
            ExtentSparkReporter spark = new ExtentSparkReporter("src/test/java/testInputAndOutput/reports/TestReports.html");

            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Playwright Otomasyon Raporu");
            spark.config().setReportName("Test Sonuçları");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Rapora sistem bilgilerini ekleyerek ortam detaylarını belgeliyoruz.
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("QA Engineer", "Ceren Kılıç");
            extent.setSystemInfo("Project", "Playwright Java Automation");
        }
        return extent;

    }
}