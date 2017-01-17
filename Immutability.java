public class Immutability {

    public static void main(String[] args) {
        Product product1 = new Product(String.valueOf(345), "HeadPhones");
        Product product2 = new Product(String.valueOf(454), "Desk");
    }

    public static final class Product {
        private final String id;
        private final String name;

        public Product(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
