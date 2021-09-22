package api.wpm.currency;

import api.wpm.currency.domain.Rate;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.util.Objects.isNull;

public class CurrencyService {
    private static BigDecimal usd = getUsdScale();
    private static BigDecimal euro = getEuroScale();
    private static LocalDate lastUpdate;

    public static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public static BigDecimal getEuroScale() {
        if (isOldData(euro))
            return euro;
        euro = getCurrencyByCode("451").Cur_OfficialRate;
        lastUpdate = LocalDate.now();
        return euro;
    }

    public static BigDecimal getUsdScale() {
        if (isOldData(usd))
            return usd;
        usd = getCurrencyByCode("431").Cur_OfficialRate;
        lastUpdate = LocalDate.now();
        return usd;
    }

    private static boolean isOldData(final BigDecimal scale) {
        return !isNull(scale) && lastUpdate.getDayOfYear() >= LocalDate.now()
                .getDayOfYear();
    }

    private static Rate getCurrencyByCode(final String codeOfCurrency) {
        return REST_TEMPLATE.getForObject(String.format("https://www.nbrb.by/api/exrates/rates/%s?periodicity=0", codeOfCurrency), Rate.class);
    }
}
