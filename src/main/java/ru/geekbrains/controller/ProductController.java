package ru.geekbrains.controller;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.persist.entity.Customer;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.CustomerRepository;
import ru.geekbrains.persist.repo.ProductRepo;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.repo.ProductSpecificatiom;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductRepo productRepo;


    @GetMapping
    public String allProducts(Model model,
                              @RequestParam(value = "title", required = false) String title,
                              @RequestParam(value = "price", required = false) BigDecimal price,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size       ) {


            logger.info("Filtering by name: {}", title);


/*      PageRequest -наследник Pageable
                         PageRequest extends AbstractPageRequest
                         abstract class AbstractPageRequest implements Pageable, Serializable
                                                                    без сортировки:
      PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, size.orElse(3));
                                                   с сорторовкой  по цене по возрастанию:                       */
        PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, size.orElse(4),
                Sort.by("title").ascending().and(Sort.by("price").ascending()));
            Specification<Product> spec = ProductSpecificatiom.trueLiteral();
            if (title != null && !title.isEmpty()) {
                spec = spec.and(ProductSpecificatiom.titleLike(title));
            }
            if (price != null ) {
                spec = spec.and(ProductSpecificatiom.priceGreaterThan(price));
            }
                                        // добавляем разные атрибуты с данными для отображения
//        model.addAttribute("productPage", productRepository.findAll(spec, pageRequest));
        model.addAttribute("productPage", productRepository.findAll(spec, pageRequest));
//        model.addAttribute("products",productList);
        return "products";         // возвращаем имя представления которое будет отображеть модель
    }

    @GetMapping("/max")
    public String maxPrice(Model model) {
        Product max =new Product();
        max = productRepo.findMaxPrice();
        model.addAttribute("products", max);
        return "prices";
    }
    @GetMapping("/min")
    public String minPrice(Model model) {
        Product min =new Product();
//        min = productRepo.findMinPrice();
        min = productRepository.findMinPrice();
        model.addAttribute("products", min);
        return "prices";
    }
    @GetMapping("/min-max")
    public String minmaxPrice(Model model) {

        List<Product> productList;
        productList = productRepo.findMinMaxPrice();
        model.addAttribute("products", productList);
        return "prices";
    }

}
