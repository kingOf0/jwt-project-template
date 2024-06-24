package com.kingof0.jwtprojecttemplate;

import com.kingof0.jwtprojecttemplate.model.entity.CurrencyType;
import com.kingof0.jwtprojecttemplate.model.entity.trip.*;
import com.kingof0.jwtprojecttemplate.model.entity.user.Authority;
import com.kingof0.jwtprojecttemplate.security.service.UserServiceImp;
import com.kingof0.jwtprojecttemplate.service.TripPriceService;
import com.kingof0.jwtprojecttemplate.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
@RequiredArgsConstructor
public class JWTProjectTemplate implements CommandLineRunner {

    private final UserServiceImp userServiceImp;
    private final TripService tripService;
    private final TripPriceService tripPriceService;

    public static void main(String[] args) {
        SpringApplication.run(JWTProjectTemplate.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userServiceImp.loadUserByUsername("admin") == null) {
            userServiceImp.register("admin", "admin", List.of(
                    Authority.ADMINISTRATOR
            ));
        }

        createTrip("Balkan", "Eşsiz Batum Turu", "Gürcistan", 100.0, 150.0, CurrencyType.TURKISH_LIRA);

        createTrip("Yurt İçi", "Eşsiz Antalya Turu", "Türkiye", 100.0, 150.0, CurrencyType.TURKISH_LIRA);
        createTrip("Yurt İçi", "Eşsiz Amasya Turu", "Türkiye", 100.0, 150.0, CurrencyType.TURKISH_LIRA);

        Logger.getGlobal().info(LocalDate.now().toString());
        Logger.getGlobal().info(LocalDateTime.now().toString());
    }


    private void createTrip(String category, String title, String subtitle, Double price, Double strikethroughPrice, CurrencyType currencyType) {
        TripLocation tripLocation = new TripLocation();
        tripLocation.setLatitude(41.0082);
        tripLocation.setLongitude(28.9784);
        tripLocation.setCity("Istanbul");
        tripLocation.setCountry("Turkey");
        tripLocation.setAddress("Istanbul, Turkey");
        tripLocation.setDistrict("Kadikoy");
        tripLocation.setName("Kadıköy");

        Trip trip = new Trip();

        trip.setTitle(title);
        trip.setSubtitle(subtitle);
        trip.setDescription("8 gün 7 gece Batum turu. 5 yıldızlı otelde konaklama. Sabah kahvaltısı ve akşam yemeği dahil.");

        trip.setImages(new ArrayList<>());

        trip.setQuota(10);
        trip.setDuration(10);

        trip.setStartDate(LocalDate.now());
        trip.setEndDate(LocalDate.now().plusDays(10));

        trip.setLocation(tripLocation);

        TripCategory tripCategory = new TripCategory();
        tripCategory.setName(category);
        tripCategory.setRoute(category.toLowerCase().replace(" ", "-"));

        trip.setCategory(tripCategory);

        TripDetails tripDetails = new TripDetails();

        tripDetails.setIncludedServices(List.of(
                "Uçak Bileti",
                "5 yıldızlı otelde konaklama",
                "Sabah kahvaltısı",
                "Akşam yemeği",
                "Ulaşım",
                "Rehberlik Hizmeti",
                "Havalimanı Transferi"
        ));
        tripDetails.setExcludedServices(List.of(
                "Zorunlu Seyahat Sigortası"
        ));

        TripFAQ tripFAQ = new TripFAQ();
        tripFAQ.setQuestion("Nasıl ödeme yapabilirim?");
        tripFAQ.setAnswer("Ödeme işlemleri sırasında kredi kartı, banka havalesi ve nakit ödeme seçeneklerinden birini tercih edebilirsiniz.");

        TripFAQ tripFAQ2 = new TripFAQ();
        tripFAQ2.setQuestion("Rezervasyon iptali nasıl yapılır?");
        tripFAQ2.setAnswer("Rezervasyon iptali için 7 gün öncesinden bildirim yapılması gerekmektedir. 7 gün öncesinden yapılan iptallerde ödeme iadesi yapılacaktır.");

        tripDetails.setFaqs(List.of(tripFAQ, tripFAQ2));

        trip.setDetails(tripDetails);

        trip.setPrice(price);
        trip.setStrikethroughPrice(strikethroughPrice);
        trip.setCurrencyType(currencyType);

        TripComment tripComment = new TripComment();
        tripComment.setAuthor("İbrahim D.");
        tripComment.setComment("Çok güzel bir turdu. Herkese tavsiye ederim.");
        tripComment.setRating(5);
        tripComment.setDate(LocalDate.now().minusDays(10).minusMonths(2).minusYears(2));

        TripComment tripComment2 = new TripComment();
        tripComment2.setAuthor("Mehmet A.");
        tripComment2.setComment("Ailemle birlikte katıldık. Çocuklarımız da çok eğlendi. Batum'un harika manzaralarını gördük ve çok güzel vakit geçirdik. Teşekkürler.");
        tripComment2.setRating(5);
        tripComment2.setDate(LocalDate.now().minusDays(5).minusMonths(1).minusYears(1));

        trip.setComments(List.of(tripComment, tripComment2));

        TripInformation tripInformation = new TripInformation();

        tripInformation.setTitle("Gürcistana Merhaba");
        tripInformation.setDescription("Gürcistan, Türkiye'ye komşu bir ülkedir. Gürcistan'ın başkenti Tiflis'tir. Gürcistan'ın resmi dili Gürcücedir. Gürcistan'ın para birimi Lari'dir. Gürcistan'ın yüzölçümü 69.700 km²'dir. Gürcistan'ın nüfusu 3.729.600'dür. Gürcistan'ın en yüksek noktası 5.201 metre ile Kazbek Dağı'dır. Gürcistan'ın en büyük gölü Paliastomi Gölü'dür. Gürcistan'ın en büyük nehri Kura Nehri'dir. Gürcistan'ın en büyük gölü Paliastomi Gölü'dür. Gürcistan'ın en büyük gölü Paliastomi Gölü'dür. Gürcistan'ın en büyük gölü Paliastomi Gölü'dür. Gürcistan'ın en büyük gölü Paliastomi Gölü'dür. Gürcistan'ın en büyük gölü Paliastomi Gölü'dür. Gürcistan'ın en büyük gölü Paliastomi Gölü'dür. Gürcistan'ın en büyük gölü Paliastomi Gölü'dür.");

        TripInformation tripInformation2 = new TripInformation();
        tripInformation2.setTitle("Batum'a Merhaba");
        tripInformation2.setDescription("Batum, Gürcistan'ın en büyük üçüncü şehri ve özerk cumhuriyetidir. Batum, Karadeniz kıyısında yer almaktadır. Batum, Gürcistan'ın en önemli turizm merkezlerinden biridir. Batum, Gürcistan'ın en önemli turizm merkezlerinden biridir. Batum, Gürcistan'ın en önemli turizm merkezlerinden biridir. Batum, Gürcistan'ın en önemli turizm merkezlerinden biridir. Batum, Gürcistan'ın en önemli turizm merkezlerinden biridir. Batum, Gürcistan'ın en önemli turizm merkezlerinden biridir. Batum, Gürcistan'ın en önemli turizm merkezlerinden biridir. Batum, Gürcistan'ın en önemli turizm merkezlerinden biridir.");

        TripInformation tripInformation3 = new TripInformation();
        tripInformation3.setTitle("Doğal Güzellikler");
        tripInformation3.setDescription("Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir.");

        TripInformation tripInformation4 = new TripInformation();
        tripInformation4.setTitle("Doğal Güzellikler");
        tripInformation4.setDescription("Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir. Gürcistan, doğal güzellikleri ile ünlü bir ülkedir.");

        trip.setInformations(List.of(tripInformation, tripInformation2, tripInformation3, tripInformation4));

        tripService.save(trip);
        trip.setRoute(title.toLowerCase().replace(" ", "-") + "-" + trip.getId());
        tripService.save(trip);


        TripRoom tripRoom = new TripRoom();
        tripRoom.setTrip(trip);
        tripRoom.setPrice(150.0);
        tripRoom.setGuestCount(1);
        tripRoom.setCurrencyType(CurrencyType.TURKISH_LIRA);
        tripRoom.setName("1 Yetişkin (Tek Kişilik Oda)");

        tripPriceService.save(tripRoom);

        TripRoom tripRoom2 = new TripRoom();

        tripRoom2.setTrip(trip);
        tripRoom2.setPrice(225.0);
        tripRoom2.setGuestCount(2);
        tripRoom2.setCurrencyType(CurrencyType.TURKISH_LIRA);
        tripRoom2.setName("2 Yetişkin (İki Kişilik Oda)");

        tripPriceService.save(tripRoom2);

        TripRoom tripRoom3 = new TripRoom();
        tripRoom3.setTrip(trip);
        tripRoom3.setPrice(250.0);
        tripRoom3.setGuestCount(3);
        tripRoom3.setCurrencyType(CurrencyType.TURKISH_LIRA);
        tripRoom3.setName("2 Yetişkin 1 Çocuk (0-2 Yaş)");

        tripPriceService.save(tripRoom3);

        TripRoom tripRoom4 = new TripRoom();
        tripRoom4.setTrip(trip);
        tripRoom4.setPrice(250.0);
        tripRoom4.setGuestCount(3);
        tripRoom4.setCurrencyType(CurrencyType.TURKISH_LIRA);
        tripRoom4.setName("2 Yetişkin 1 Çocuk (2-12 Yaş)");

        tripPriceService.save(tripRoom4);
    }

}
