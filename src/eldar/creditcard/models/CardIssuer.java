package eldar.creditcard.models;

import java.time.LocalDate;

public enum CardIssuer {
    VISA {
        @Override
        public double getRate() {
            LocalDate today = LocalDate.now();
            double rate = ((double) today.getYear())/((double) today.getMonthValue());
            return validateRate(rate) / 100;
        }
    },
    NARA {
        @Override
        public double getRate() {
            LocalDate today = LocalDate.now();
            double rate = today.getDayOfMonth() * NARA_RATE_MULTIPLIER;
            return validateRate(rate) / 100;
        }
    },
    AMEX {
        @Override
        public double getRate() {
            LocalDate today = LocalDate.now();
            double rate = today.getMonthValue() * AMEX_RATE_MULTIPLIER;
            return validateRate(rate) / 100;
        }
    };

    private static final double MIN_RATE = 0.3;
    private static final double MAX_RATE = 5;

    private static final double NARA_RATE_MULTIPLIER = 0.5;
    private static final double AMEX_RATE_MULTIPLIER = 0.1;

    public double getRate() {
        return 0.0;
    }

    public double validateRate(double rate) {
        if(rate < MIN_RATE) {
            return MIN_RATE;
        } else if(rate > MAX_RATE)
            return MAX_RATE;

        return rate;
    }
}
