package api.wpm.currency.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Rates {
    private List<Rate> rates;
}