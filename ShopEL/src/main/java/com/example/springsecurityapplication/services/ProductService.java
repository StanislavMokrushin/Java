package com.example.springsecurityapplication.services;


import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
//    получение всех пользователей
        public List<Product> getAllProduct(){
        return productRepository.findAll();

    }

//    получение продукта по айди
        public Product getProductId(int id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }


//    сохраняем объект продукта который пришел с формы
    @Transactional
    public void saveProduct(Product product){
        productRepository.save(product);

    }


// обнолвение информации о товаре тоже через метод сайв только сначала задаем айди
    @Transactional
    public void updateProduct(int id, Product product){
        product.setId(id);
        productRepository.save(product);
    }
//    удаляем товар по айди
    @Transactional
    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }
// данный метод позволит получить товар по наименованию
    public Product getProductFindByTitle(Product product){
        Optional<Product> product_db = productRepository.findByTitle(product.getTitle());
//        если получаем то возвращаем, если нет, то нал
        return product_db.orElse(null);
}


}
