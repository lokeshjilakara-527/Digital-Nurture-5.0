public class SearchTest {

    public static void main(String[] args) {

        Product[] products = {
                new Product(101, "Laptop", "Electronics"),
                new Product(102, "Phone", "Electronics"),
                new Product(103, "Shoes", "Fashion"),
                new Product(104, "Book", "Education")
        };

        Product result1 = SearchFunctions.linearSearch(products, 103);

        if (result1 != null)
            System.out.println("Linear Search Found: "
                    + result1.productName);

        Product result2 = SearchFunctions.binarySearch(products, 104);

        if (result2 != null)
            System.out.println("Binary Search Found: "
                    + result2.productName);
    }
}
