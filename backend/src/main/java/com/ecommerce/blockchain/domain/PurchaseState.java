package com.ecommerce.blockchain.domain;

/**
 * Sub PJT Ⅲ
 * 상품 구매 상태 표시를 위한 States
 */
public enum PurchaseState {
    I { // Initial - purchased
        @Override
        public String toString() {
            return "I";
        }
    },
    P { // Paid
        @Override
        public String toString() {
            return "P";
        }
    },
    S { // Sent
        @Override
        public String toString() {
            return "S";
        }
    },
    C { // Confirmed
        @Override
        public String toString() {
            return "C";
        }
    },
    X { // Cancelled
        @Override
        public String toString() {
            return "X";
        }
    }
}
