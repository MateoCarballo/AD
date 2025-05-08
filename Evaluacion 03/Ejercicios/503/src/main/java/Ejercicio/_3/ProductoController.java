package Ejercicio._3;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    //Añadir un nuevo producto
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        String response = productoService.addProduct(product);
        return ResponseEntity.ok(response);
    }
    //Obtener todos los productos
    @GetMapping("/get/All")
    public ResponseEntity<ArrayList<Product>> getAllProducts(){
        ArrayList<Product> productList= productoService.getAllProducts();
        return ResponseEntity.ok(productList);
    }
    @PostMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id){
        Product p = productoService.getProductById(id);
        //Como gestionar que el producto con ese id no exista ??
        return ResponseEntity.ok(p);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") int id){
        boolean isDeleted = false;
        if (productoService.deleteProductById(id)) isDeleted = true;
        return ResponseEntity.ok(isDeleted);
    }
}