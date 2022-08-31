package eldar.creditcard.models;

import java.time.LocalDate;

public enum CardIssuer {
    VISA {
        public double getRate() {
            LocalDate today = LocalDate.now();
            double rate = ((double) today.getYear())/((double) today.getMonthValue());
            return validateRate(rate);
        }
    },
    NARA {
        public double getRate() {
            LocalDate today = LocalDate.now();
            double rate = today.getDayOfMonth() * NARA_RATE_MULTIPLIER;
            return validateRate(rate);
        }
    },
    AMEX {
        public double getRate() {
            LocalDate today = LocalDate.now();
            double rate = today.getMonthValue() * AMEX_RATE_MULTIPLIER;
            return validateRate(rate);
        }
    };

    private static final double MIN_RATE = 0.3;
    private static final double MAX_RATE = 5;

    private static final double NARA_RATE_MULTIPLIER = 0.5;
    private static final double AMEX_RATE_MULTIPLIER = 0.1;

    public double validateRate(double rate) {
        if(rate < MIN_RATE) {
            return MIN_RATE;
        } else if(rate > MAX_RATE)
            return MAX_RATE;

        return rate;
    }
}
