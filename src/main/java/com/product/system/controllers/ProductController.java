package com.product.system.controllers;

import com.product.system.entity.ProductEntity;
import com.product.system.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Sonikb on 11.08.2017.
 */
@Controller
@RequestMapping(value = "/product", method = RequestMethod.GET)
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView productsList() {
        ModelAndView modelAndView = new ModelAndView();
        List<ProductEntity> productEntityList = productService.getAll();
        modelAndView.addObject("productList", productEntityList);
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public ModelAndView validateProduct() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productFromPage", new ProductEntity());
        modelAndView.setViewName("add_product");
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("productFromPage") ProductEntity productEntity) {
        productService.save(productEntity);
        ModelAndView modelAndView = new ModelAndView();
        List<ProductEntity> productEntityList = productService.getAll();
        modelAndView.addObject("productList", productEntityList);
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        ProductEntity productEntity = productService.getById(Integer.parseInt(id));
        productService.remove(productEntity);
        List<ProductEntity> productEntityList = productService.getAll();
        modelAndView.addObject("productList", productEntityList);
        modelAndView.setViewName("products");
        return modelAndView;
    }


    @RequestMapping(value = "/update_product{id}", method = RequestMethod.GET)
    public ModelAndView validateUser(@PathVariable("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("updateProduct", new ProductEntity());
        ProductEntity productEntity = productService.getById(Integer.parseInt(id));
        modelAndView.addObject("product", productEntity);
        modelAndView.setViewName("update");
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("updateProduct") ProductEntity productEntity,
                               @RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        productEntity.setId(id);
        productService.update(productEntity);
        System.out.println("Продукт успешно изменен!");
        List<ProductEntity> productEntityList = productService.getAll();
        modelAndView.addObject("productList", productEntityList);
        modelAndView.setViewName("products");
        return modelAndView;
    }
}
