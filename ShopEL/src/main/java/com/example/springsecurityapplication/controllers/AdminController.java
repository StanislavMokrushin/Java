package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Image;
import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import com.example.springsecurityapplication.repositories.OrderRepository;
import com.example.springsecurityapplication.repositories.PersonRepository;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.services.OrderService;
import com.example.springsecurityapplication.services.ProductService;
import com.example.springsecurityapplication.util.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController {

//    указывает путь к фото
    @Value("${upload.path}")
    private String uploadPath;


    private final ProductValidator productValidator;
    private final ProductService productService;

    private final OrderService orderService;

    private final OrderRepository orderRepository;



    private final PersonRepository personRepository;

    private final CategoryRepository categoryRepository;
    @Autowired
    public AdminController(ProductValidator productValidator, ProductService productService, OrderService orderService, OrderRepository orderRepository, PersonRepository personRepository, CategoryRepository categoryRepository) {
        this.productValidator = productValidator;
        this.productService = productService;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
        this.categoryRepository = categoryRepository;
    }


    //    доступ только с ролью админа и или
//    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('')")
//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('')")
//    метод по отображению главной страницы админа с выводом товаров
    @GetMapping()
    public String admin(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
//         получаем роль аутентифицированного пользователя
        String role =personDetails.getPerson().getRole();
        if (role.equals("ROLE_USER")) {
            return "redirect:/index";
        }
//        админ может получать список продуктов
        model.addAttribute("products", productService.getAllProduct());
        return "admin/admin";
    }

//    метод по отображению формы добавления товара
    @GetMapping("/product/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
//        отображение категорий
        model.addAttribute("category", categoryRepository.findAll());
        return "product/addProduct";
    }

//    метод по добавлению объекта с формы в таблицу product, MultipartFile - хранилище для файлов, отдельный файл для хранения файлов, BindingResult - для помещения ошибок при валидации
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one")MultipartFile file_one, @RequestParam("file_two")MultipartFile file_two, @RequestParam("file_three")MultipartFile file_three, @RequestParam("file_four")MultipartFile file_four, @RequestParam("file_five")MultipartFile file_five) throws IOException {

        productValidator.validate(product, bindingResult);
        if(bindingResult.hasErrors()){
            return "product/addProduct";
        }
//        проверка на пустоту файла
        if(file_one != null){
//            объект по хранению пути сохранения (директория для файла)
            File uploadDir = new File(uploadPath);


//            проверка существования пути
            if(!uploadDir.exists()){
//                если нет пути то создаем ее
                uploadDir.mkdir();
            }
//            создаем уникальное имя файла (UUID - представляет неизменный универскальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
//            file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
//            загружаем файл по указанной директории
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
//            создаем объект фото
            Image image = new Image();
//            продукт из формы
            image.setProduct(product);
//            наименование берем которое сформировалось выше
            image.setFileName(resultFileName);
            product.addImageProduct(image);

        }

        //        проверка на пустоту файла
        if(file_two != null){
//            объект по хранению пути сохранения (директория для файла)
            File uploadDir = new File(uploadPath);
//            проверка существования пути
            if(!uploadDir.exists()){
//                если нет пути то создаем ее
                uploadDir.mkdir();
            }
//            создаем уникальное имя файла (UUID - представляет неизменный универскальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
//            file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
//            загружаем файл по указанной директории
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
//            создаем объект фото
            Image image = new Image();
//            продукт из формы
            image.setProduct(product);
//            наименование берем которое сформировалось выше
            image.setFileName(resultFileName);
            product.addImageProduct(image);

        }

        //        проверка на пустоту файла
        if(file_three != null){
//            объект по хранению пути сохранения (директория для файла)
            File uploadDir = new File(uploadPath);
//            проверка существования пути
            if(!uploadDir.exists()){
//                если нет пути то создаем ее
                uploadDir.mkdir();
            }
//            создаем уникальное имя файла (UUID - представляет неизменный универскальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
//            file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
//            загружаем файл по указанной директории
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
//            создаем объект фото
            Image image = new Image();
//            продукт из формы
            image.setProduct(product);
//            наименование берем которое сформировалось выше
            image.setFileName(resultFileName);
            product.addImageProduct(image);

        }

        //        проверка на пустоту файла
        if(file_four != null){
//            объект по хранению пути сохранения (директория для файла)
            File uploadDir = new File(uploadPath);
//            проверка существования пути
            if(!uploadDir.exists()){
//                если нет пути то создаем ее
                uploadDir.mkdir();
            }
//            создаем уникальное имя файла (UUID - представляет неизменный универскальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
//            file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
//            загружаем файл по указанной директории
            file_four.transferTo(new File(uploadPath + "/" + resultFileName));
//            создаем объект фото
            Image image = new Image();
//            продукт из формы
            image.setProduct(product);
//            наименование берем которое сформировалось выше
            image.setFileName(resultFileName);
            product.addImageProduct(image);

        }

        //        проверка на пустоту файла
        if(file_five != null){
//            объект по хранению пути сохранения (директория для файла)
            File uploadDir = new File(uploadPath);
//            проверка существования пути
            if(!uploadDir.exists()){
//                если нет пути то создаем ее
                uploadDir.mkdir();
            }
//            создаем уникальное имя файла (UUID - представляет неизменный универскальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
//            file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
//            загружаем файл по указанной директории
            file_five.transferTo(new File(uploadPath + "/" + resultFileName));
//            создаем объект фото
            Image image = new Image();
//            продукт из формы
            image.setProduct(product);
//            наименование берем которое сформировалось выше
            image.setFileName(resultFileName);
            product.addImageProduct(image);

        }

        productService.saveProduct(product);
        return "redirect:/admin";

    }
// метод по удалению товара по айди
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/admin";
    }
// метод по получению товара по айди и отображения шаблона редактирования
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("editProduct", productService.getProductId(id));
//        категории
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("editProduct") Product product, @PathVariable("id") int id){
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }

    @GetMapping("/users")
    public String userList (Model model){
        List<Person> usersList = personRepository.findAll();
        model.addAttribute("users", usersList);
        return "admin/users";
    }

    @GetMapping("/users/editRoleUser/{id}")
    public String editRoleUser(@PathVariable("id") int id){
        personRepository.SetRoleUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/editRoleAdmin/{id}")
    public String editRoleAdmin(@PathVariable("id") int id) {
        personRepository.SetRoleAdmin(id);
        return "redirect:/admin/users";
    }

// возвращает страницу с заказами
    @GetMapping("/orders")
    public String order(Model model){
//        List<Order> orderList = orderRepository.findAll();
//        model.addAttribute("orders", orderList);
        model.addAttribute("orders", orderService.getAllOrder());
        return "/orders";
    }


// страница с подробной инфо о заказе
    @GetMapping("order/edit/{id}")
    public String editOrder(@PathVariable("id") int id, Model model){
        model.addAttribute("editOrder", orderService.getOrderId(id));
        return "admin/editOrder";
    }
//редактирование заказа
    @PostMapping ("order/edit/{id}")
    public String editOrder(@ModelAttribute("editOrder") Order order, @PathVariable("id") int id){
        orderService.updateOrder(id, order);
        return "redirect:/admin/orders";
    }

    @GetMapping("order/delete/{id}")
    public String deleteOrder(@PathVariable("id") int id){
           orderService.deleteOrder(id);
           return "redirect:/admin/orders";
        }



}
