package api.wpm.currency.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//This data too bizarre for me(
public class Rate {
    public int Cur_ID;
    public LocalDateTime Date;
    public String Cur_Abbreviation;
    public int Cur_Scale;
    public String Cur_Name;
    public BigDecimal Cur_OfficialRate;
}
