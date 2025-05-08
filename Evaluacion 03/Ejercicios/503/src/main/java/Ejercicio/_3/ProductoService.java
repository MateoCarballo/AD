package Ejercicio._3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductoService {

    private ArrayList<Product> products;

    @Autowired
    public ProductoService(ArrayList<Product> products) {
        this.products = products;
    }

    public String addProduct(Product product){
        products.add(product);
        return "Product addded succesfully";
    }

    public ArrayList<Product> getAllProducts(){
        return products;
    }

    public Product getProductById(int id){
        return searchProductById(id);
    }

    private Product searchProductById(int id){
        Product productSearched = null;
        for (Product p: products){
            if (p.getId() == id){
                productSearched = p;
            }
        }
        return productSearched;
    }

    public boolean deleteProductById(int id){
        boolean isDeleted = false;
        for (Product p: products){
            products.remove(p);
            isDeleted = true;
        }
        return isDeleted;
    }
}
