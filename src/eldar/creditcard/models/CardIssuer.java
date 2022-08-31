package eldar.creditcard.models;

public enum CardIssuer {
    VISA {
        @Override
        public double getRate(Operation op) {
            double year = op.getCard().getExpireDate().getYear() % 100;
            double month = op.getCard().getExpireDate().getMonthValue();
            double rate = year/month;
            return validateRate(rate) / 100;
        }
    },
    NARA {
        @Override
        public double getRate(Operation op) {
            double rate = op.getPurchaseDate().getDayOfMonth() * NARA_RATE_MULTIPLIER;
            return validateRate(rate) / 100;
        }
    },
    AMEX {
        @Override
        public double getRate(Operation op) {
            double rate = op.getCard().getExpireDate().getMonthValue() * AMEX_RATE_MULTIPLIER;
            return validateRate(rate) / 100;
        }
    };

    private static final double MIN_RATE = 0.3;
    private static final double MAX_RATE = 5;

    private static final double NARA_RATE_MULTIPLIER = 0.5;
    private static final double AMEX_RATE_MULTIPLIER = 0.1;

    public double getRate(Operation op) {
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
