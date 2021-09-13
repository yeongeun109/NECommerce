package com.ecommerce.blockchain.domain;

import lombok.Data;
import org.web3j.tuples.generated.Tuple3;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * Sub PJT Ⅲ 과제 3
 * 이력 컨트랙트 데이터를 위한 클래스
 */
@Data
public class Record {
    enum State {Purchased, Paid, Sent, Complete, Cancelled, Refunded, End}
    private String state;
    private LocalDateTime when;
    private String by; // user's wallet account

    public static Record getInstance(Tuple3<BigInteger, BigInteger, String> r) {
        Record record = new Record();
        record.setState(getStateString(r.getValue1()));
        record.setWhen(LocalDateTime.ofInstant(Instant.ofEpochSecond(r.getValue2().longValue()),
                TimeZone.getDefault().toZoneId()));
        record.setBy(r.getValue3());
        return record;
    }

    private static String getStateString(BigInteger number) {
        switch(number.intValue()) {
            case 0:
                return "Purchased";
            case 1:
                return "Paid";
            case 2:
                return "Sent";
            case 3:
                return "Complete";
            case 4:
                return "Cancelled";
            case 5:
                return "Refunded";
            case 6:
                return "End";
            default:
                return "";
        }
    }
}
